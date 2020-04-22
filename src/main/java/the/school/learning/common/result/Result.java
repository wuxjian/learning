package the.school.learning.common.result;

/**
 * http返回给前端的响应
 */
public class Result {
    private Integer code;
    private String msg;
    private Object data;

    public static Result success() {
        return new Result(0, null, null);
    }

    public static Result success(Object data) {
        return new Result(0, null, data);
    }

    public static Result error(String msg) {
        return new Result(500, msg, null);
    }

    public static Result error(Integer code, String msg) {
        return new Result(code, msg, null);
    }


    public Result() {
    }

    public Result(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
