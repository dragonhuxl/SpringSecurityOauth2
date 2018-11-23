package com.example.demo.common;

public class HTTPCode {

    public static final Code OK = new Code(200,"请求成功");
    public static final Code NOTREQUESTBODY = new Code(400,"请求参数中没有需要的 request body");
    public static final Code NOTFOUND = new Code(404,"无法找到资源");
    public static final Code METHOD_NOTALLOWED = new Code(405,"method not allowed(请求行中指定的请求方法不能被用于请求相应的资源)");
    public static final Code SERVER_ERR = new Code(500,"服务器遇到了一个未曾预料的状况，导致了它无法完成对请求的处理。");
    public static final Code TOKENERR = new Code(700,"Token信息有误");
    public static final Code PARAMERR = new Code(701,"请求参数错误");
    public static final Code UNAUTHORIZED = new Code(702,"权限错误");
    public static final Code UNKNOWERR = new Code(703,"未知错误，请联系管理员！");
    public static final Code ISARCHIVED = new Code(704,"任务已归档，不允许此操作！");

    /**
     * HTTP 返回码
     */
    public static class Code{
        private int code;
        private String msg;

        public Code(int code, String msg){
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public String getmsg() {
            return msg;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public void setmsg(String msg) {
            this.msg = msg;
        }
    }
}
