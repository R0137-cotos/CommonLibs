package jp.co.ricoh.cotos.commonlib.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

@Configuration
@EnableWebSecurity
public class TestWebSecurityConfig {

	@Autowired
	TestVoter testVoter;

	@Autowired
	AccessLogOutputFilter accessLogOutputFilter;

	@Autowired
	MultipleReadEnableFilter multipleReadEnableFilter;

	public void configure(WebSecurity web) throws Exception {
		web.ignoring().requestMatchers("/test/api/swagger-ui.html");
		web.debug(true);
	}

	@Bean
	protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.addFilterAfter(accessLogOutputFilter, PreAuthenticationFilter.class);
		http.addFilterAfter(multipleReadEnableFilter, AccessLogOutputFilter.class);

		http.csrf(csrf -> csrf.disable())

				// 認証処理用のフィルターを追加
				.addFilter(preAuthenticatedProcessingFilter()).exceptionHandling(exception -> exception.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
				// 成功・失敗処理のハンドラーを追加
				.formLogin(Customizer.withDefaults()).logout(logout -> logout.logoutSuccessHandler(new SimpleUrlLogoutSuccessHandler()))
				// 認可処理用のインスタンスを追加
				.authorizeHttpRequests(auth -> auth.anyRequest().authenticated()).authenticationManager(authenticationManager())
				// 認証情報を取得できなければ、401エラー
				.anonymous(anonymous -> anonymous.disable()).exceptionHandling(exception -> exception.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)));

		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		return http.build();
	}

	// フィルター
	@Bean
	public AbstractPreAuthenticatedProcessingFilter preAuthenticatedProcessingFilter() throws Exception {
		PreAuthenticationFilter filter = new PreAuthenticationFilter();
		filter.setAuthenticationManager(authenticationManager());
		return filter;
	}

	@Bean
	public AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> authenticationUserDetailsService() {
		return new CotosUserDetailsService();
	}

	// フィルター登録
	@Bean
	public PreAuthenticatedAuthenticationProvider preAuthenticatedAuthenticationProvider() {
		PreAuthenticatedAuthenticationProvider provider = new PreAuthenticatedAuthenticationProvider();
		provider.setPreAuthenticatedUserDetailsService(authenticationUserDetailsService());
		provider.setUserDetailsChecker(new AccountStatusUserDetailsChecker());
		return provider;
	}

	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(preAuthenticatedAuthenticationProvider());
	}
	
	public AuthenticationManager authenticationManager() {
		return new ProviderManager(Arrays.asList(preAuthenticatedAuthenticationProvider()));
	}
}
