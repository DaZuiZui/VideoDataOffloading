/**
 * 杨易达
 * @email: y51288033@outlook.com
 *         y51288033@gmail.com
 */
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

//主入口路由
const mainRouters = 
[
    {
    path: '/',
    name: 'HelloWorld',
    component: resolve=>(require(["@/components/HelloWorld"],resolve))
    },
    {
      path: '/userindex',
      name: 'userindex',
      component: resolve=>(require(["@/view/modules/user/index"],resolve))
    },
    {
      path: '/douyin',
      name: 'douyin',
      component: resolve=>(require(["@/view/modules/douyin/index"],resolve))
    }
]

const router = new Router({
  mode: "hash",
  routes:  mainRouters,
})

/**
 * 路由前置环绕
 * 可以做的事情，比如做单点登入，或者查看用户是否有权限访问此页面
 * @param to    去哪个页面来
 * @param from  从哪个页面来
 * @param next  如果放行则是next(),跳转其他的next();
 */
router.beforeEach(async (to, from, next)=>{
  console.log("router前置环绕");

  next()
})

/**
 * 路由后置环绕
 * @param to    去哪个页面来
 * @param from  从哪个页面来
 */
router.afterEach(async (to, from)=>{
  console.log("router后置环绕");
})


export default router