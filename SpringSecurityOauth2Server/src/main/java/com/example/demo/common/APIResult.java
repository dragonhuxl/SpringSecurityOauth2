package com.example.demo.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class APIResult {

    private static final Logger logger = LoggerFactory.getLogger(APIResult.class);

    private String datetime;    //请求时间
    private HTTPCode.Code code; //请求返回码
    private String msg;         //返回信息
    private boolean success;    //请求结果 true | false
    private Object objects;     //返回结果

    //构造函数
    /**
     * @Description: http code赋值。使用场景：如鉴权报错，传入HttpCode.UNAUTHORIZED ;如操作成功，传入HttpCode.OK
     * @Author: liumeng
     * @Date: 2018/11/2 16:13
     * @Param: [code]
     * @Return:
     **/
    public APIResult(HTTPCode.Code code){
        this(code, code.getmsg(), code.getCode()==200, null);
    }

    /**
     * @Description: 传入对象赋值，code默认为成功标志OK， success 为 true。使用场景：如查询对象，返回对象。
     * @Author: liumeng
     * @Date: 2018/11/2 16:14
     * @Param: [objects]
     * @Return:
     **/
    public APIResult(Object objects){
        this(HTTPCode.OK, HTTPCode.OK.getmsg(), true, objects);
    }
    /**
     * @Description: http code、msg赋值。使用场景：如新建对象参数异常，返回HttpCode.PARAMERR 异常码, msg返回“**参数不应该为空”
     * @Author: liumeng
     * @Date: 2018/11/2 16:19
     * @Param: [code, msg]
     * @Return:
     **/
    public APIResult(HTTPCode.Code code, String msg){
        this(code, msg, code.getCode()==200, null);
    }
    /**
     * @Description:  http code、msg、success 赋值，使用场景：更新报错，code为请求成功OK，success为false，msg为“更新失败。”
     * @Author: liumeng
     * @Date: 2018/11/2 16:21
     * @Param: [code, msg, success]
     * @Return:
     **/
    public APIResult(HTTPCode.Code code, String msg, boolean success){
        this(code, msg,  success, null);
    }
    /**
     * @Description:
     * @Author: liumeng
     * @Date: 2018/11/2 16:25
     * @Param: code Http返回码
     * @Param: msg 返回信息
     * @Param: success操作是否成功
     * @Param: objects 返回结果对象
     * @Return:
     **/
    public APIResult(HTTPCode.Code code, String msg, boolean success, Object objects){

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.datetime = df.format(new Date());
        this.code = code;
        this.msg = msg;
        this.success = success;
        this.objects = objects;
        logger.info(this.toString());
    }

    public String getDatetime() {
        return datetime;
    }

    public HTTPCode.Code getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public Object getObjects() {
        return objects;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public void setCode(HTTPCode.Code code) {
        this.code = code;
        this.success = code.getCode()==200;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setObjects(Object objects) {
        this.objects = objects;
    }

    @Override
    public String toString() {
        return "APIResult{" +
                "datetime='" + datetime + '\'' +
                ", code=" + code +
                ", msg='" + msg + '\'' +
                ", success=" + success +
                ", objects=" + objects +
                '}';
    }
}
