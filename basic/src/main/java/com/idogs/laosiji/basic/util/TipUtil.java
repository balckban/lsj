package com.idogs.laosiji.basic.util;

/**
 * 提示语工具类，所有的提示语常量
 * @author y
 *
 */
public class TipUtil {
	
	//0.异常============================================================
    /**
     * 请求失败
     */
    public static String error = "请求失败，请重新请求";
	//1.校验码============================================================
	/**
	 * 校验码超时
	 */
	public static String vcodeExpire = "校验码超时，请重新请求";
	/**
	 * 校验码错误
	 */
	public static String vcodeWrong = "校验码出错，请重新请求";

	/**
	 * 发送短信验证码成功
	 */
	public static String sendSmsSuccess = "发送短信成功";
	//2.短信验证码============================================================
	/**
	 * 短信超限
	 */
	public static String smsTooMuch = "当天发送短信次数超过限制";
	/**
	 * 短信超频
	 */
	public static String smsTooSoon = "请求短信太频繁，请稍后再试";
	/**
	 * 第三方发送短信失败
	 */
	public static String smsSendFail = "请求发送短信失败";
	/**
	 * 短信入库失败
	 */
	public static String smsSqlFail = "短信发送过程出现异常";
	/**
	 * 三方发送短信请求过快
	 */
	public static String smsAskTooSoon = "请求发送短信频率过快";
	/**
	 * 三方发送短信请求过多
	 */
	public static String smsAskTooMuch = "请求发送短信数量超上限";
	/**
	 * 三方发送短信服务问题
	 */
	public static String smsAskError = "短信服务出现故障，请稍后";
	
	/**
	 * 短信验证码不存在
	 */
	public static String smsNotExists = "短信验证码输入有误，请重试";
	/**
	 * 短信验证码过期（5分钟）
	 */
	public static String smsNotValid = "短信验证码过期，请重新发送";
	//3.注册登录信息提示============================================================
	/**
	 * 用户已经存在，无法重复注册
	 */
	public static String userAlreadyExists = "手机号码已经存在，请误重复注册";
	
	/**
	 * 手机号码格式有误
	 */
	public static String userWrongPhoneNumber = "手机号码错误";
	/**
	 * 手机号码为空
	 */
	public static String userPhoneNumberIsEmpty = "手机号码不能为空";

	/**
	 * 手机未注册
	 */
	public static String userNotRegister = "手机号码未注册";
    /**
     * 找不到用户
     */
    public static String userIsNull = "找不到用户";
	/**
	 * 密码为空
	 */
	public static String passwordIsEmpty = "密码不能为空";
	/**
	 * 密码错误
	 */
	public static String userLoginError = "密码错误登录失败";
	/**
	 * 保存用户错误
	 */
	public static String userSqlIsWrong = "数据库保存用户错误";
	
	//4.资讯发布=================================================================
	/**
	 * 资讯为空
	 */
	public static String artTitleIsNull = "资讯标题不能为空";
	/**
	 * 资讯描述为空
	 */
	public static String artDescIsNull = "资讯描述不能为空";
	/**
	 * 作者信息为空
	 */
	public static String artUserIsNull = "资讯作者不能为空";
	/**
	 * 文章类目为空
	 */
	public static String artCartIsNull = "资讯类目不能为空";
	/**
	 * 文章内容为空
	 */
	public static String artHtmlIsNull = "资讯内容不能为空";
	//5.综合网络错误=================================================================
	/**
	 * 网络有问题
	 */
	public static String netWorkError = "网络有问题，稍后再请求该操作";
	//6.车辆信息错误=================================================================
	
	/**
	 * 车辆型号不能为空
	 */
	public static String carDescriptionIsNotNULL = "车辆信号描述为空";
	/**
	 * 车辆颜色
	 */
	public static String carcolorIsNotNULL = "车辆颜色不能为空";
	/**
	 * 车牌号
	 */
	public static String carNumIsNotNULL = "车牌号不能为空";
	public static String carNumIsError = "车牌号不能为空";
	/**
	 * 编辑个人信息失败
	 */
	public static String updateUserFailed="编辑个人信息失败";
	/**
	 * 手机号修改不成功
	 */
	public static String updatePhoneFailed="手机号修改不成功";
	/**
	 * 
	 */
	public static String limitPhone="30天允许修改一次 ";
	/**
	 * 编辑车辆信息不成功
	 */
	public static String updateCarInfoFailed="编辑车辆信息不成功！";
	/**
	 * 该用户未添加车辆
	 */
	public static String userHaveNoCar="该用户未添加车辆";
	/**
	 * 车辆id不能为空
	 */
	public static String carIdIsNotNull="车辆id不能为空";
	/**
	 * 车辆行驶证图片路径不能为空
	 */
	public static String carlienceimgurlIsNotNUll="车辆行驶证图片路径不能为空";
	/**
	 * 行驶证图片上传失败
	 */
	public static String carlienceimgurlFailed="行驶证图片上传失败";
	/**
	 * 绑定手机号失败
	 */
	public static String bindPhoneFailed="绑定手机号失败";
    //7.订单信息=================================================================
    /**
     * 订单为空
     */
    public static String orderIsNull="订单为空";
    /**
     * 订单被抢
     */
    public static String orderHasTake="订单已被抢";
    /**
     * 订单更新成功
     */
    public static String orderUpdateSuccess="订单更新成功";
    /**
     * 订单更新失败
     */
    public static String orderUpdateFail="订单更新失败";
    //8.位置信息=================================================================
    /**
     * 位置更新失败
     */
    public static String locationUpdateFail="更新位置信息失败";
   //9.优惠券信息=================================================================
    /**
     * 该用户券不存在
     */
	public static String discountIsNull="该用户券不存在";
	/**
	 * 优惠券id不能为空
	 */
	public static String dicountIdIsNull="优惠券id不能为空";
	/**
	 * 优惠券用户领取失败
	 */
	 public static  String discountFailed = "优惠券用户领取失败";
	 /**
	  * 该车辆不存在
	  */
	public static String carIsNull="该车辆不存在,请拨打114查询该车辆";
	/**
	 * 用户id不能为空
	 */
	public static String uidIsNull="用户id不能为空";
	/**
	 * 查询用户优惠券失败
	 */
	public static String discountSelectFailed="查询用户优惠券失败 ,请稍后再试";
	/**
	 * 查询好友失败
	 */
	public static String findFriendFailed="获取好友失败，请重试";
	/**
	 * 好友表id不能为空
	 */
	public static String friendIdIsNull="请选择好友";
	/**
	 * 好友状态不能为空
	 */
	public static String friendStatusIsNull="请选择添加或删除好友";
	/**
	 * 该车辆为绑定用户
	 */
	public static String carNotUser="该车辆为绑定用户";
	/**
	 * 云之讯隐私号解绑失败
	 */
	public static String lateAgain="云之讯隐私号码解绑失败，请联系管理员";

	/**
	 * 微信id为空
	 */
	public static String wxopenidIsEmpty = "微信id为空";
	
	//9.第三方错误=================================================================
	
	/**
	 * （未授权）请求要求身份验证。对于需要token的接口，服务器可能返回此响应
	 */
	public static String unauthorized="（未授权）请求要求身份验证";
	
	/**
	 * 此用户不存在
	 */
	public static String service_resource_not_found="此用户不存在";
	
	/**
	 * 第三方发送id不能为空
	 */
	public static String sidIsNotNull="短信发送id不能为空";
	
	/**
	 * 环信用户注册失败
	 */
	public static final String huanxinIMuserFailed = "平台未能帮您在第三方平台注册，可能会影响您的消息通知";
	/**
	 * 验证码不能为空
	 */
	public static final String yzcodeIsNotNull = "短信验证码不能为空";
	/**
	 * 验证码不能为空
	 */
	public static final String yzcodeWrongLength = "验证码长度不对";
	/**
	 * 验证码不能为空
	 */
	public static final String yzcodeWrongChar = "验证码只能包含数字和英文";
	
	/**
	 * 员工类型
	 */
	public static final String empTypeIsnull = "员工类型不能为空";
	
	/**
	 * 员工id
	 */
	public static final String empIdIsnull = "员工id不能为空";
	
	/**
	 * 小区id
	 */
	public static final String vidIsnull = "小区表id不能为空";
	
	/**
	 * 验证私人停车位id
	 */
	public static final String privateParkIdIsNull = "私人停车位id不能为空";
	
	/**
	 * 商业区id不能为空
	 */
	public static final String buildIdIsNull = "商业物业id不能为空";
	
	/**
	 * 物业id不能为空
	 */
	public static final String servIdIsNull = "物业id不能为空";

	//===================================================================

	public static final String commonWaiting = "请等待";

	/**
	 * Token非法，只能重新登录
	 */
	public static final String tokenIllegal = "token_illegal";

	/**
	 * Token超时
	 */
	public static final String tokenTimeout = "token_timeout";

}
