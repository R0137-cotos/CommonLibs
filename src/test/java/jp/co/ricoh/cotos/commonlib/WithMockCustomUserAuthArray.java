package jp.co.ricoh.cotos.commonlib;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.security.test.context.support.WithSecurityContext;

import jp.co.ricoh.cotos.commonlib.entity.master.UrlAuthMaster.ActionDiv;

/**
 * {@link jp.co.ricoh.cotos.commonlib.WithMockCustomUser WithMockCustomUser}の複数権限指定可能なよう変更したクラス
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = WithMockCustomUserAuthArraySecurityContextFactory.class)
public @interface WithMockCustomUserAuthArray {

	String momEmployeeId() default "mid";

	String singleUserId() default "sid";

	String origin() default "cotos.ricoh.co.jp";

	String applicationId() default "cotos_dev";

	boolean isSuperUser() default false;

	boolean isDummyUser() default false;

	String jwt() default "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJvcmlnaW4iOiJjb3Rvcy5yaWNvaC5jby5qcCIsInNpbmdsZVVzZXJJZCI6InNpZCIsIm1vbUVtcElkIjoibWlkIiwiZXhwIjoyNTM0MDIyNjgzOTksImFwcGxpY2F0aW9uSWQiOiJjb3Rvc19kZXYifQ.qJBFsMJFZcLdF7jWwEafZSOQfmL1EqPVDcRuz6WvsCI";

	ActionDiv actionDiv() default ActionDiv.なし;

	SubWithMockCustomUserAuth[] auth();
}
