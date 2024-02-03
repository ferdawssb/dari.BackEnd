package dari.BackEnd.securite;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.jaas.memory.InMemoryConfiguration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.nimbusds.jose.jwk.source.ImmutableSecret;

import dari.BackEnd.entites.Utilisateur;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecuriteConfig {
	@Value("${jwt.secret}")
	private String secretKey;
	@Autowired
	


	@Bean
	public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
		PasswordEncoder passwordEncoder = passwordEncoder();

		return new InMemoryUserDetailsManager(
				User.withUsername("Ferdaous Bouzakher").password(passwordEncoder.encode("12345")).authorities("LOC").build()
				
 
		);

	}

	@Bean
	public PasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();

	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

		return httpSecurity.sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS)// le gere pas cote seveur utiliser un tokon jwt

		).csrf(csrf -> csrf.disable())
				 .cors(cors -> cors.configurationSource(configurationSource()))                 
				 .authorizeHttpRequests(ar->ar.requestMatchers("/auth/login/**").permitAll()
						 .requestMatchers("/logement/get").permitAll()
						 .requestMatchers("logement/imageLogement/{id}").permitAll() 
						 ) 
				 

				 
				.authorizeHttpRequests(ar -> ar.anyRequest().authenticated())
				// .httpBasic(Customizer.withDefaults())chaque req neccessite une authen
				.oauth2ResourceServer(oa -> oa.jwt(Customizer.withDefaults())).build();
	}

	@Bean
	public JwtEncoder jwtEncoder() {
		// String secretKey = "9faa372517ac1d389758d375 0fc0 7acf 00f5 4227 7f26 fec1
		// ce45 93e9 3f64 e338";
		return new NimbusJwtEncoder(new ImmutableSecret<>(secretKey.getBytes()));
	}

	@Bean
	public NimbusJwtDecoder jwtDecoder() {
		// String secretKey =
		// "9faa372517ac1d389758d3750fc07acf00f542277f26fec1ce4593e93f64e338";
		SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "RSA");
		return NimbusJwtDecoder.withSecretKey(secretKeySpec).macAlgorithm(MacAlgorithm.HS512).build();
	}

	@Bean
	public AuthenticationManager authenticationManager(UserDetailsService userDetailsService) {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		daoAuthenticationProvider.setUserDetailsService(userDetailsService);
		return new ProviderManager(daoAuthenticationProvider);

	}
	
	
	@Bean
	CorsConfigurationSource configurationSource() {
	    CorsConfiguration corsConfiguration = new CorsConfiguration();
	    corsConfiguration.addAllowedOrigin("*");
	    corsConfiguration.addAllowedMethod("*");
	    corsConfiguration.addAllowedHeader("*");
	    
	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", corsConfiguration);
	    
	    return source;    
	}

	
	/*@Bean
	CorsConfigurationSource configuration() {
		CorsConfiguration corsConfiguration=new CorsConfiguration();
		corsConfiguration.addAllowedOrigin("*");
		corsConfiguration.addAllowedMethod("*");
		corsConfiguration.addAllowedHeader("*");
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfiguration);
        return source;	*/
	}
	
	

