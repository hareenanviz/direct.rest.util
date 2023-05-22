package com.anvizent.rest.util;

import java.util.HashMap;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

public class AppTest {

	public static void main(String[] args) {
		RestUtil restUtil = new RestUtil(true);

		ResponseEntity<String> responseEntity = restUtil.exchange("https://core-spark-2-3-0-0-18-1-5-try-2.risingcloud.app/risingcloud/jobs", "{\n"
		        + "    \"sparkMaster\": \"local[*]\",\n" + "    \"appName\": \"test\",\n" + "    \"privateKey2\": \"anvizent\",\n"
		        + "    \"iv2\": \"AnvizentDMT IV16\",\n" + "    \"driver\": \"com.mysql.jdbc.Driver\",\n"
		        + "    \"jdbcUrl\": \"jdbc:mysql://182.18.164.13:4475/DevTesting_1009396_appdb?useSSL=false\",\n" + "    \"userName\": \"root\",\n"
		        + "    \"password2\": \"iuiCTR5-hj6esvm8TqINHw\",\n" + "    \"password\": \"Explore@09\",\n"
		        + "    \"configs\": \"SELECT config_key, config_value FROM minidwcm_config_tags_key_value_pairs WHERE  tag_id = 79 ORDER BY seq_id\",\n"
		        + "    \"values\": \"SELECT config_key, config_value FROM minidwcm_config_tags_key_value_pairs WHERE  tag_id = 80 ORDER BY seq_id\",\n"
		        + "    \"derivedComponentConfigs2\": \"SELECT config_key,config_value FROM minidwcm_config_tags_key_value_pairs where tag_id = 3 order by seq_id asc\",\n"
		        + "    \"globalValues2\": \"SELECT config_key,config_value FROM minidwcm_config_tags_key_value_pairs where tag_id = 1 order by seq_id asc\",\n"
		        + "    \"resourceConfig\": \"SELECT config_key, config_value FROM minidwcm_config_tags_key_value_pairs WHERE  tag_id = 81 ORDER BY seq_id\"\n"
		        + "}", HttpMethod.POST, new HashMap<String, String>());

		String status = "Processing";
		String status2 = "Failed";

		System.out.println(responseEntity.getStatusCode());
		System.out.println(responseEntity.getBody());
	}

}
