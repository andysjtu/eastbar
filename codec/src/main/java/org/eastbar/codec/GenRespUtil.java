package org.eastbar.codec;

/**
 * Created by andysjtu on 2015/5/9.
 */
public class GenRespUtil {

    public static GenResp createCenter2SiteSuccessResp(short recMessageId,short recMessageType){
        GenResp genResp = new GenResp.Center2SGenResp(recMessageId, recMessageType, GenResp.Status.Sucess);
        return genResp;
    }

    public static GenResp createS2ClientSucessResp(short recMessageId, short recMessageType) {
        GenResp genResp = new GenResp.S2ClientGenResp(recMessageId, recMessageType, GenResp.Status.Sucess);
        return genResp;
    }

    public static GenResp createS2ClientFailResp(short recMessageId, short recMessageType) {
        GenResp genResp = new GenResp.S2ClientGenResp(recMessageId, recMessageType, GenResp.Status.Failure);
        return genResp;
    }

    public static GenResp createS2ClientMsgErrorResp(short recMessageId, short recMessageType) {
        GenResp genResp = new GenResp.S2ClientGenResp(recMessageId, recMessageType, GenResp.Status.MessageError);
        return genResp;
    }
}
