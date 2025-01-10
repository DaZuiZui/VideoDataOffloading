/**
 * 在vue cli 3.xbn的时候使用
 */
const path = require('path');
const UglifyJsPlugin = require('uglifyjs-webpack-plugin') // 去掉注释
const CompressionWebpackPlugin = require('compression-webpack-plugin'); // 开启压缩
const { HashedModuleIdsPlugin } = require('webpack');

function resolve(dir) {
    return path.join(__dirname, dir)
}

const isProduction = process.env.NODE_ENV === 'production';

// cdn预加载使用
const externals = {
    'vue': 'Vue',
    'vue-router': 'VueRouter',
    'vuex': 'Vuex',
    'axios': 'axios',
    "element-ui": "ELEMENT"
}

const cdn = {
    // 开发环境
    dev: {
        css: [
            'https://unpkg.com/element-ui/lib/theme-chalk/index.css'
        ],
        js: []
    },
    // 生产环境
    build: {
        css: [
            'https://unpkg.com/element-ui/lib/theme-chalk/index.css'
        ],
        js: [
            'https://cdn.jsdelivr.net/npm/vue@2.5.17/dist/vue.min.js',
            'https://cdn.jsdelivr.net/npm/vue-router@3.0.1/dist/vue-router.min.js',
            'https://cdn.jsdelivr.net/npm/vuex@3.0.1/dist/vuex.min.js',
            'https://cdn.jsdelivr.net/npm/axios@0.18.0/dist/axios.min.js',
            'https://unpkg.com/element-ui/lib/index.js'
        ]
    }
}

module.exports = {

    lintOnSave: false, // 关闭eslint
    productionSourceMap: false,
    publicPath: './', 
    outputDir: process.env.outputDir, // 生成文件的目录名称
    chainWebpack: config => {

        config.resolve.alias
            .set('@', resolve('src'))

        // 压缩图片
        config.module
            .rule('images')
            .test(/\.(png|jpe?g|gif|svg)(\?.*)?$/)
            .use('image-webpack-loader')
            .loader('image-webpack-loader')
            .options({ bypassOnDebug: true })

        // webpack 会默认给commonChunk打进chunk-vendors，所以需要对webpack的配置进行delete
        config.optimization.delete('splitChunks')

        config.plugin('html').tap(args => {
            if (process.env.NODE_ENV === 'production') {
                args[0].cdn = cdn.build
            }
            if (process.env.NODE_ENV === 'development') {
                args[0].cdn = cdn.dev
            }
            return args
        })
        
        config
            .plugin('webpack-bundle-analyzer')
            .use(require('webpack-bundle-analyzer').BundleAnalyzerPlugin)
    },

    configureWebpack: config => {
        const plugins = [];

        if (isProduction) {
            plugins.push(
                new UglifyJsPlugin({
                    uglifyOptions: {
                        output: {
                            comments: false, // 去掉注释
                        },
                        warnings: false,
                        compress: {
                            drop_console: true,
                            drop_debugger: false,
                            pure_funcs: ['console.log']//移除console
                        }
                    }
                })
            )
            // 服务器也要相应开启gzip
            plugins.push(
                new CompressionWebpackPlugin({
                    algorithm: 'gzip',
                    test: /\.(js|css)$/,// 匹配文件名
                    threshold: 10000, // 对超过10k的数据压缩
                    deleteOriginalAssets: false, // 不删除源文件
                    minRatio: 0.8 // 压缩比
                })
            )

            // 用于根据模块的相对路径生成 hash 作为模块 id, 一般用于生产环境
            plugins.push(
                new HashedModuleIdsPlugin()
            )

            // 开启分离js
            config.optimization = {
                runtimeChunk: 'single',
                splitChunks: {
                    chunks: 'all',
                    maxInitialRequests: Infinity,
                    minSize: 1000 * 60,
                    cacheGroups: {
                        vendor: {
                            test: /[\\/]node_modules[\\/]/,
                            name(module) {
                                // 排除node_modules 然后吧 @ 替换为空 ,考虑到服务器的兼容
                                const packageName = module.context.match(/[\\/]node_modules[\\/](.*?)([\\/]|$)/)[1]
                                return `npm.${packageName.replace('@', '')}`
                            }
                        }
                    }
                }
            };

            // 取消webpack警告的性能提示
            config.performance = {
                hints: 'warning',
                //入口起点的最大体积
                maxEntrypointSize: 1000 * 500,
                //生成文件的最大体积
                maxAssetSize: 1000 * 1000,
                //只给出 js 文件的性能提示
                assetFilter: function (assetFilename) {
                    return assetFilename.endsWith('.js');
                }
            }

            // 打包时npm包转CDN
            config.externals = externals;
        }

        return { plugins }
    },

    pluginOptions: {
        // 配置全局less
        'style-resources-loader': {
            preProcessor: 'less',
            patterns: [resolve('./src/style/theme.less')]
        }
    },
    devServer: {
        open: true, // 自动启动浏览器
        host: '192.168.0.109', // localhost
        port: 8001, // 端口号
        https: false,
        hotOnly: false, // 热更新
        proxy: {
            // 匹配所有/api/hello开头的请求进行转发
            '/api/hello/*': {
                target: 'http://192.168.0.109:8001',
                // pathRewrite:{'^/student':''}    用正则表达式把所有请求中的/student替换为空字符串再进行转发
                ws: true,  // 默认为：true 是否支持webSocket
                changeOrigin: false  // 默认为true 控制是否对请求的浏览器撒谎，请求头中host值，true时如果要访问的浏览器端口为66，则撒谎为自己是同ip端口为66的请求，false时转发时如实回答自己是代理服务器
            },
        
            // 可以配置多个
            // 匹配所有/xxx开头的请求进行转发
            /*
            '/api/user': {
                target: 'http://localhost:312',
                ws: true,  
                changeOrigin: false
            }
            */
        }
    }
}