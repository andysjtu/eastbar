package org.eastbar.site.bizproxy;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import org.eastbar.codec.DozerUtil;
import org.eastbar.codec.UserInfo;
import org.eastbar.site.biz.model.C1001;
import org.eastbar.site.biz.model.C1101;
import org.eastbar.site.biz.model.C1102;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;

/**
 * Created by AndySJTU on 2015/6/15.
 */
public class BizOuterHandler extends ChannelInboundHandlerAdapter {
    private final BizProxyServer server;
    public final static Logger logger = LoggerFactory.getLogger(BizOuterHandler.class);

    private static int SC_OK = 200; // 成功.
    private static int SC_EXCEPTION = 300; // 由于未知原因，服务方抛出了异常.
    private static int SC_BAD_REQUEST = 410; // 请求格式错误.
    private static int SC_BAD_REQUEST_TYPE = 411; // 错误的请求类型.
    private static int SC_MISS_REQUIRE_FIELDS = 412; // 请求中缺少必须的字段.
    private static int SC_AUTHENTICATE_FAIL = 420; // 身份认证失败.
    private static int SC_INVALID_SESSION_ID = 421; // 会话ID不正确.
    private static int SC_INVALID_ACCOUNT = 431; // 账号不正确.
    private static int SC_INVALID_NAME = 432; // 名称不正确.
    private static int SC_INVALID_CERT_ID = 433; // 证件号码不正确.
    private static int SC_INVALID_CERT_TYPE = 434; // 证件类型不正确.
    private static int SC_INVALID_NATION = 435; // 国家不正确.
    private static int SC_INVALID_TIME = 441; // 时间格式不正确.
    private static int SC_INVALID_TERMIP = 451; // 终端IP格式不正确.
    private static int SC_NO_AVAILABLE_SERVER = 500; // 请求服务端可能不存在


    public BizOuterHandler(BizProxyServer server) {
        this.server = server;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {

            ByteBuf buf = (ByteBuf) msg;
            int type = buf.readInt();// 包类型
            int len1001 = buf.readableBytes();
            System.out.println("包类型是: " + len1001);
            byte[] lastBytes = new byte[len1001];
            buf.readBytes(lastBytes);
            String value = new String(lastBytes);// 包内容
            logger.info("content is : " + value);
            switch (type) {
                case 1001:
                    String[] connect = value.trim().split("\0");
                    C1001 c1001 = new C1001();
                    c1001.setVersion(connect[0]);
                    c1001.setUserName(connect[1]);
                    c1001.setPassWord(connect[2]);
                    String sessionId = "netbar";
                    ByteBuf newBuf = builderPacketHead(SC_OK, sessionId);
                    ctx.channel().writeAndFlush(newBuf);

                    break;
                case 1002:
                    logger.warn("收到1002事件，没有处理");
                    break;
                case 1101:
                    String[] login = value.trim().split("\0");
//                System.out.println("login is : "+login);
//                System.out.println("loin.length is  :"+login.length);
                    if (login.length == 9) {
                        C1101 c1101 = new C1101();
                        c1101.setVersion(login[0]);
                        c1101.setSessionId(login[1]);
                        c1101.setAccount(login[2]);
                        c1101.setName(new String(
                                new BASE64Decoder().decodeBuffer(login[3]), "GBK"));
                        c1101.setIdType(login[4]);
                        c1101.setId(new String(new BASE64Decoder()
                                .decodeBuffer(login[5]), "GBK"));
                        c1101.setAuthOrg(new String(new BASE64Decoder()
                                .decodeBuffer(login[6]), "GBK"));
                        c1101.setIp(login[7]);
                        c1101.setLoginTime(login[8]);
                        UserInfo userInfo = new UserInfo();
                        DozerUtil.copyProperties(c1101, userInfo);
                        System.out.println("收到的消息1101是: " + userInfo);
                        server.registerCustomerLogin(userInfo);
                    }
                    break;
                case 1102:
                    String[] logout = value.trim().split("\0");
//                System.out.println("logout is : "+logout);
//                System.out.println("logout.length is  :"+logout.length);
                    if (logout.length > 9) {
                        C1102 c1102 = new C1102();
                        c1102.setVersion(logout[0]);
                        c1102.setSessionId(logout[1]);
                        c1102.setAccount(logout[2]);
                        c1102.setName(new String(new BASE64Decoder()
                                .decodeBuffer(logout[3]), "GBK"));
                        c1102.setIdType(logout[4]);
                        c1102.setId(new String(new BASE64Decoder()
                                .decodeBuffer(logout[5]), "GBK"));
                        c1102.setAuthOrg(new String(new BASE64Decoder()
                                .decodeBuffer(logout[6]), "GBK"));
                        c1102.setIp(logout[7]);
                        c1102.setLoginTime(logout[8]);
                        c1102.setLogoutTime(logout[9]);
                        UserInfo userInfo = new UserInfo();
                        DozerUtil.copyProperties(c1102, userInfo);
                        System.out.println("收到的消息1102是: " + userInfo);
                        server.registerCustomerLogout(userInfo);
                    }
                    break;
                case 1103:
                    logger.warn("收到1103事件，没有处理");
                    break;
                case 1104:
                    logger.warn("收到1104事件，没有处理");
                    break;
                case 1105:
                    logger.warn("收到1105事件，没有处理");
                    break;
                case 1111:
                    logger.warn("收到1111事件，没有处理");
                    break;
                case 1112:
                    logger.warn("收到1112事件，没有处理");
                    //TODO
                    break;
                case 1113:
                    logger.warn("收到1113事件，没有处理");
                    break;
                case 1121:
                    logger.warn("收到1121事件，没有处理");

                    break;
                case 1122:
                    logger.warn("收到1122事件，没有处理");
                    break;
                default:
                    logger.warn("收到其他类型事件，没有处理");
            }
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        logger.warn("Unexpected exception from downstream.........", cause.getMessage());
        ctx.close();
    }

    public ByteBuf builderPacketHead(int messType, String sessionId) {
        ByteBuf buf = Unpooled.buffer();
        buf.writeInt(messType);// 返回类型
        buf.writeBytes(addString(sessionId));
        buf.writeByte('\0');
        return buf;
    }

    public static byte[] addString(String strs) {
        return strs.getBytes();
    }
}
