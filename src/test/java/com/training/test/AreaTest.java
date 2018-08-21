package com.training.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.training.common.service.CommonService;
import com.training.model.AreaModel;
import com.training.service.AreaService;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationConext.xml")//加载配置文件
public class AreaTest {

    @Autowired
    private AreaService areaService;
    @Autowired
    private CommonService commonService;

    @Test
    public void test() throws IOException {
        long start = System.currentTimeMillis();
        InputStream inputStream = AreaTest.class.getClassLoader().getResourceAsStream("area.properties");
        Properties properties = new Properties();
        properties.load(new InputStreamReader(inputStream, "GBK"));
        String result = properties.getProperty("area");
        JSONObject root = JSONObject.parseObject(result);
        Set rooKey = root.keySet();
        Iterator<String> iter = rooKey.iterator();

        List<AreaModel> all = new ArrayList<>();

        int provCount = 10;
        while (iter.hasNext()) {
            String key = iter.next();
            JSONObject prov = root.getJSONObject(key);
            AreaModel areaModel = new AreaModel();
            areaModel.setName((String) prov.get("name"));

            String provCode = Integer.toString(provCount++);

            areaModel.setCode(provCode);
            areaModel.setpCode("1");
            //areaService.insert(areaModel);
            all.add(areaModel);
            Object obj = prov.get("child");
            if (obj instanceof JSONArray) {
                JSONArray tempArray = (JSONArray) obj;
                if (tempArray.isEmpty()) {
                    continue;
                }
            }

            JSONObject citys = prov.getJSONObject("child");

            Set cityKey = citys.keySet();
            Iterator<String> cs = cityKey.iterator();

            int cityCount = 1;
            while (cs.hasNext()) {
                String c = cs.next();
                JSONObject city = citys.getJSONObject(c);
                String cityCode = Integer.toString(cityCount++);
                cityCode = cityCode.length() != 2 ? "0" + cityCode : cityCode;
                cityCode = provCode + cityCode;
                AreaModel areaModelCity = new AreaModel();
                areaModelCity.setName((String) city.get("name"));
                areaModelCity.setCode(cityCode);
                areaModelCity.setpCode(provCode);
                all.add(areaModelCity);
                //areaService.insert(areaModelCity);

                System.out.println("cityCode:" + cityCode);
                Object obj2 = city.get("child");
                if (obj2 instanceof JSONArray) {
                    JSONArray tempArray = (JSONArray) obj2;
                    if (tempArray.isEmpty()) {
                        continue;
                    }
                }

                JSONObject areas = city.getJSONObject("child");
                Set areaKey = areas.keySet();
                Iterator<String> as = areaKey.iterator();
                int areaCount = 1;
                while (as.hasNext()) {
                    String a = as.next();
                    String areaCode = Integer.toString(areaCount++);
                    areaCode = areaCode.length() != 2 ? "0" + areaCode : areaCode;
                    areaCode = cityCode + areaCode;

                    AreaModel areaModel2 = new AreaModel();
                    areaModel2.setName(areas.getString(a));
                    areaModel2.setCode(areaCode);
                    areaModel2.setpCode(cityCode);
                    //areaService.insert(areaModel2);
                    all.add(areaModel2);
                }
            }
        }

        areaService.saveAll(all);

        System.out.println("time:" + (System.currentTimeMillis() - start)/1000f + "s");
    }


}
