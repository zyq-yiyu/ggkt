package com.atguigu.ggkt.result;//package com.atguigu.ggkt.result;
//
//import lombok.Data;
//
//@Data
//public class Result<T> {
//
//    private  Integer code;//状态码
//
//    private String message;//返回状态信息(成功失败)
//
//    private T data;//返回数据
//
//    public Result(){}
//
//    //成功的方法，没有data数据
////    public static<T> Result<T> ok(){
////        Result<T> result = new Result<>();
////        result . setCode(200) ;
////        result . setMessage("成功");
////        return result;
////    }
//
//    //失败的方法，没有data数据
////    public static<T> Result<T> fail(){
////        Result<T> result = new Result<>();
////        result . setCode(201) ;
////        result . setMessage("失败");
////        return result;
////    }
//    //成功的方法，有data数据
//    public static<T> Result<T> ok(T data){
//        Result<T> result = new Result<>();
//        if (data != null){
//            result.setData(data);
//        }
//        result . setCode(200) ;
//        result . setMessage("成功");
//        return result;
//    }
//
//    //失败的方法，有data数据
//    public static<T> Result<T> fail(T data){
//        Result<T> result = new Result<>();
//        if (data != null){
//            result.setData(data);
//        }
//        result . setCode(201) ;
//        result . setMessage("失败");
//        return result;
//    }
//
//    public Result<T> message(String msg){
//        this.setMessage(msg);
//        return this;
//    }
//
//    public Result<T> code(Integer code){
//        this.setCode(code);
//        return this;
//    }
//}
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 全局统一返回结果类
 *
 */
@Data
@ApiModel(value = "全局统一返回结果")
public class Result<T> {

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private T data;

    public Result(){}

    public static <T> Result<T> build(T body, Integer code, String message) {
        Result<T> result = new Result<T>();
        if (body != null) {
            result.setData(body);
        }
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    public static<T> Result<T> ok(){
        return Result.ok(null);
    }

    /**
     * 操作成功
     * @param data  baseCategory1List
     * @param <T>
     * @return
     */
    public static<T> Result<T> ok(T data){
        return build(data,20000,"成功");
    }

    public static<T> Result<T> fail(){
        return Result.fail(null);
    }

    /**
     * 操作失败
     * @param data
     * @param <T>
     * @return
     */
    public static<T> Result<T> fail(T data){
        return build(data, 20001,"失败");
    }

    public Result<T> message(String msg){
        this.setMessage(msg);
        return this;
    }

    public Result<T> code(Integer code){
        this.setCode(code);
        return this;
    }
}
