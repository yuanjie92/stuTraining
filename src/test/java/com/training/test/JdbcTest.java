package com.training.test;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.training.model.AreaModel;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

public class JdbcTest {

    PreparedStatement ps = null;
    Connection conn = null;

    @Before
    public void before() throws SQLException {

        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/training?useUnicode=true&amp;characterEncoding=utf8", "root", "root");

    }

    @Test
    public void sayHello() {
        System.out.println("hello world");
    }

    @Test
    public void intsertArea() throws SQLException, IOException {
        long start = System.currentTimeMillis();
        List<AreaModel> list = getAll();
        String insert = "insert into area (code,name,pcode) values (?,?,?)";
        ps = conn.prepareStatement(insert);
        conn.setAutoCommit(false);
        for (AreaModel model : list) {
            ps.setString(1, model.getCode());
            ps.setString(2, model.getName());
            ps.setString(3, model.getpCode());
            ps.addBatch();
        }
        ps.executeBatch();
        conn.commit();
        System.out.println("end: " + (System.currentTimeMillis() - start) / 1000f + "s");
    }


    private List<AreaModel> getAll() throws IOException {
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
    return all;
    }

}
