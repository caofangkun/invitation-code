package com.github.caofangkun.invitation;
import com.google.common.hash.Hashing;

public class InviteCodeDigest {

    /** 自定义进制(0,1没有加入,容易与o,l混淆) */
    private static final char[] r = new char[]{
        'F', 'L', 'G', 'W', '5', 'X', 'C', '3',
        '%', 'Z', 'M', '6', '7', 'Y', 'R', 'T',
        '2', 'H', '!', '8', 'D', 'V', 'E', 'J',
        '4', 'K', 'Q', 'P', 'U', 'A', 'N', 'B',
        '9', 'S', '^', '&', '?'};

    /** 进制长度 */
    private static final int binLen = r.length;

    /** 随机设定的值 */
    private static final long startNumber = 10242563L;

  /**
   *
   * @param email  注册的邮箱地址
   * @param appName 注册的APP名称
   * @return 随机邀请码
   */
    public static String inviteCode(String email, String appName) {
        String authStr = email.trim() + ":+" + appName.trim();
        long res = Hashing.md5().hashBytes(authStr.getBytes()).asLong();
        return idToCode(Math.abs(res));
    }

    private static String idToCode(long id,long costomStartNumber) {
    	if(costomStartNumber<0){
    		costomStartNumber = startNumber;
    	}
    	id += costomStartNumber;
        char[] buf=new char[64];
        int charPos=64;

        while((id / binLen) > 0) {
            int ind=(int)(id % binLen);
            buf[--charPos]=r[ind];
            id /= binLen;
        }
        buf[--charPos]=r[(int)(id % binLen)];
        String str=new String(buf, charPos, (64 - charPos));
        return str.toUpperCase();
    }
    private static String idToCode(long idL){
    	return idToCode(idL,startNumber);
    }
    private static String idToCode(String id){
    	long idL = Long.parseLong(id);
    	return idToCode(idL,startNumber);
    }
    private static String idToCode(String id,long costomStartNumber){
    	long idL = Long.parseLong(id);
    	return idToCode(idL,costomStartNumber);
    }

    public static long codeToId(String code) {
    	code = code.toUpperCase();
        char chs[]=code.toCharArray();
        long res=0L;
        for(int i=0; i < chs.length; i++) {
            int ind=0;
            for(int j=0; j < binLen; j++) {
                if(chs[i] == r[j]) {
                    ind=j;
                    break;
                }
            }
            if(i > 0) {
                res=res * binLen + ind;
            } else {
                res=ind;
            }
        }
        res -= startNumber;
        return res;
    }
}