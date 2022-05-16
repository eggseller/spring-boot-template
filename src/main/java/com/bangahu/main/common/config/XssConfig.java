
public class XssConfig {
	private final ObjectMapper objMapper = ObjectMapper();
	
	@Bean
	public MappingJackson2HttpMessageConvert jsonEscapeConvert() {
		ObjectMapper mapper = objMapper.copy();
		mapper.configure(DesrializationFeature.FAIL_ON_UNKOWN_PROPERTIES, false);
		mapper.getFactory().setCharacterEscapes(new HTMLCharacterEscapes());
		mapper.setDateFormat(new SimpleDateFormat("yyy-MM-dd HH:mm:ss.SSS"));
		return new MappingJackson2HttpMessageConvert(mapper);
	}
}