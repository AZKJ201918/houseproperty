package com.azkj.houseproperty;


import com.azkj.houseproperty.common.exception.PropertyExcption;
import com.azkj.houseproperty.common.utils.MongodbUtil;
import com.azkj.houseproperty.entity.Coordinates;
import com.azkj.houseproperty.entity.Distribution;
import com.azkj.houseproperty.entity.Piont;
import com.azkj.houseproperty.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HousepropertyApplicationTests {



	@Autowired
	@Qualifier("UserServiceImpl")
	private UserService userService;

	@Autowired
	private MongodbUtil mongodbUtil;
	@Test
	public  void contextLoads() throws PropertyExcption {

//		Distribution distribution=new Distribution();
//		distribution.setCardid("12312312");
//		distribution.setRealname("21312312");
//		distribution.setPhone("17683716034");
//		distribution.setIdentity("123123123");
//		distribution.setUuid("1321312312");
//		distribution.setVerCoder("213123");
//		distribution.setId(1);
//		userService.InstrDistribution(distribution);
		Piont piont=new Piont();
		piont.setLongitude(106.063339);
		piont.setLatitude(30.547347);
		List<Coordinates> coordinates=mongodbUtil.findthenear(piont);
		coordinates.stream().forEach(
				parm ->{
					System.out.println(parm.Coordinates().toString());
				}
		);
		System.out.println(1);
	}

}
