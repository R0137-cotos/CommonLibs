package jp.co.ricoh.cotos.commonlib;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import jp.co.ricoh.cotos.commonlib.entity.master.UrlAuthMaster.AuthDiv;
import jp.co.ricoh.cotos.commonlib.security.mom.MomAuthorityService.AuthLevel;

@Retention(RetentionPolicy.RUNTIME)
public @interface SubWithMockCustomUserAuth {

	AuthDiv authDiv() default AuthDiv.なし;

	AuthLevel authLevel() default AuthLevel.不可;
}
