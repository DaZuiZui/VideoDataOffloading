package com.example.demo.domain;

import java.io.Serializable;


/**
 * 响应信息主体
 *
 * @author ruoyi
 */
public class R<T> implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 创建 */
    public static final int CREATE = HttpStatus.CREATED;

    /** 成功 */
    public static final int SUCCESS = HttpStatus.SUCCESS;

    /** 需要支付 */
    public static final int PAYMENT_REQUIRED = HttpStatus.PAYMENT_REQUIRED;

    /** 密码错误 */
    public static final int PASSWORD_ERROR = HttpStatus.PASSWORD_ERROR;
    /** 账号错误 */
    public static final int ACCOUNT_ERROR = HttpStatus.ACCOUNT_ERROR;
    /** 手机号错误 */
    public static final int PHONENUMBER_ERROR = HttpStatus.PHONENUMBER_ERROR;

    /** 失败 */
    public static final int FAIL = HttpStatus.ERROR;

    private int code;

    private String msg;

    private T data;

    public static <T> R<T> ok()
    {
        return restResult(null, SUCCESS, "操作成功");
    }

    public static <T> R<T> ok(T data)
    {
        return restResult(data, SUCCESS, "操作成功");
    }

    public static <T> R<T> ok(T data, String msg)
    {
        return restResult(data, SUCCESS, msg);
    }

    public static <T> R<T> fail()
    {
        return restResult(null, FAIL, "操作失败");
    }

    public static <T> R<T> fail(String msg)
    {
        return restResult(null, FAIL, msg);
    }

    public static <T> R<T> fail(T data)
    {
        return restResult(data, FAIL, "操作失败");
    }

    public static <T> R<T> fail(T data, String msg)
    {
        return restResult(data, FAIL, msg);
    }

    public static <T> R<T> fail(int code, String msg)
    {
        return restResult(null, code, msg);
    }

    public static <T> R<T> create()
    {
        return restResult(null, CREATE, "创建成功");
    }

    public static <T> R<T> create(String msg) {
        return restResult(null, CREATE, msg);
    }

    public static <T> R<T> create(T data)
    {
        return restResult(data, CREATE, "创建成功");
    }

    public static <T> R<T> create(T data, String msg)
    {
        return restResult(data, CREATE, msg);
    }

    public static <T> R<T> update() {
        return restResult(null, CREATE, "更新成功");
    }

    public static <T> R<T> update(String msg) {
        return restResult(null, CREATE, msg);
    }

    public static <T> R<T> update(T data)
    {
        return restResult(data, CREATE, "更新成功");
    }

    public static <T> R<T> update(T data, String msg)
    {
        return restResult(data, CREATE, msg);
    }

    public static <T> R<T> paymentRequire()
    {
        return restResult(null, PAYMENT_REQUIRED, "需要支付");
    }

    public static <T> R<T> paymentRequire(T data)
    {
        return restResult(data, PAYMENT_REQUIRED, "需要支付");
    }

    public static <T> R<T> paymentRequire(T data, String msg)
    {
        return restResult(data, PAYMENT_REQUIRED, msg);
    }

    public static <T> R<T> pwdError()
    {
        return restResult(null,PASSWORD_ERROR, "密码错误");
    }

    public static <T> R<T> pwdError(T data)
    {
        return restResult(data,PASSWORD_ERROR, "密码错误");
    }
    public static <T> R<T> ACCOUNT_ERROR()
    {
        return restResult(null,PASSWORD_ERROR, "账号错误");
    }

    public static <T> R<T> ACCOUNT_ERROR(T data)
    {
        return restResult(data,PASSWORD_ERROR, "账号错误");
    }

    public static <T> R<T> PHONENUMBER_ERROR()
    {
        return restResult(null,PASSWORD_ERROR, "手机号码错误");
    }

    public static <T> R<T> PHONENUMBER_ERROR(T data)
    {
        return restResult(data,PASSWORD_ERROR, "手机号码错误");
    }

    public static <T> R<T> pwdError(T data, String msg)
    {
        return restResult(data,PASSWORD_ERROR, msg);
    }
    private static <T> R<T> restResult(T data, int code, String msg)
    {
        R<T> apiResult = new R<>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        return apiResult;
    }

    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    public T getData()
    {
        return data;
    }

    public void setData(T data)
    {
        this.data = data;
    }

    public static <T> Boolean isError(R<T> ret)
    {
        return !isSuccess(ret);
    }

    public static <T> Boolean isSuccess(R<T> ret)
    {
        return R.SUCCESS == ret.getCode();
    }
}
