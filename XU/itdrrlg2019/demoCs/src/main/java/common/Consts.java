package common;

public class Consts {

//    用户相关状态

    //成功状态信息
    public  static  final  int SUCCESS=200;
    //失败状态信息
    public  static  final  int DEFEAT=100;
    //未登录状态信息
    public  static  final  int DISLOGIN=10;
    //数据为空
    public  static  final  int ISNULL=11;
    //权限不足
    public  static  final  int ISNOTNULL=12;
    //数据不存在
    public  static  final  int ISNOTHAVA=13;


    public  static  final  String CURRENTUSER="current_user";

    public  static final String TRADE_SUCCESS= "TRADE_SUCCESS";

    public static  final String AUTOLOGINTOKEN="autoLoginToken";


    public interface Cart{
      String LIMITQUANTITYSUCCESS="LIMIT_NUM_SUCCESS";
      String LIMITQUANTITYDEFEAT="LIMIT_NUM_FAIL ";
    }

//    FAILED

    public  enum  UsersEnum{
        NEED_LOGIN(2,"需要登录"),
        NO_PRIVILEGE(3,"用户未登录");


        private  int  code;
        private String desc;

        private UsersEnum(int code,String desc){
            this.code=code;
            this.desc=desc;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }


    public  enum  RoleEnum{

        ROLE_ADMIN(0,"管理员"),
        ROLE_CUSTOMER(1,"普通用户")
        ;

        private  int  code;
        private String desc;
        private RoleEnum(int code,String desc){
            this.code=code;
            this.desc=desc;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    public  enum ProductStatusEnum{

        PRODUCT_ONLINE(1,"在售"),
        PRODUCT_OFFLINE(2,"下架"),
        PRODUCT_DELETE(3,"删除")
        ;
        private  int  code;
        private String desc;
        private ProductStatusEnum(int code,String desc){
            this.code=code;
            this.desc=desc;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }


    public  enum  CartCheckedEnum{

        PRODUCT_CHECKED(1,"已勾选"),
        PRODUCT_UNCHECKED(0,"未勾选")
        ;

        private  int  code;
        private String desc;
        private CartCheckedEnum(int code,String desc){
            this.code=code;
            this.desc=desc;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    public  enum OrderStatusEnum{

        ORDER_CANCELED(0,"已取消"),
        ORDER_UN_PAY(10,"未付款"),
        ORDER_PAYED(20,"已付款"),
        ORDER_SEND(40,"已发货"),
        ORDER_SUCCESS(50,"交易成功"),
        ORDER_CLOSED(60,"交易关闭")
        ;
        private  int  code;
        private String desc;
        private OrderStatusEnum(int code,String desc){
            this.code=code;
            this.desc=desc;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }


        public  static  OrderStatusEnum codeOf(Integer code){
            for(OrderStatusEnum orderStatusEnum: values()){
                if(code==orderStatusEnum.getCode()){
                    return  orderStatusEnum;
                }
            }
            return  null;
        }


    }

    public  enum PaymentEnum{

        ONLINE(1,"线上支付")
        ;
        private  int  code;
        private String desc;
        private PaymentEnum(int code,String desc){
            this.code=code;
            this.desc=desc;
        }

        public  static  PaymentEnum codeOf(Integer code){
            for(PaymentEnum paymentEnum: values()){
                if(code==paymentEnum.getCode()){
                    return  paymentEnum;
                }
            }
            return  null;
        }


        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    public  enum PaymentPlatformEnum{

        ALIPAY(1,"支付宝")
        ;
        private  int  code;
        private String desc;
        private PaymentPlatformEnum(int code,String desc){
            this.code=code;
            this.desc=desc;
        }




        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

}
