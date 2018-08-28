package com.github.caofangkun.invitation;

import org.junit.Test;

public class InviteCodeDigestTest {

  @Test
  public void testInviteCode() {
    String inviteCodeDigest =  InviteCodeDigest.inviteCode("123456789dddddddddddddasasas@gmail.com", "com.github.cfk.df.d.d.f.d.d.");
    System.out.println(inviteCodeDigest);
    System.out.println(InviteCodeDigest.codeToId(inviteCodeDigest));
  }
}