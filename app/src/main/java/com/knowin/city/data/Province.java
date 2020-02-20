package com.knowin.city.data;


import java.util.HashMap;

public class Province {

     static HashMap<String, Integer> provinces = new HashMap<>(40);
    static HashMap<Integer, String> idToProvinces = new HashMap<>(40);
     static boolean inited = false;

    /**
     * 读取省份ID，减少数据库存储数据量及检索工作量, 注意，所有省份ID数字都不能改变。
     * @param province
     * @return
     */
     static public int getProvinceId(String province){
        if(!inited){
            initProvinces();
        }

        if(province==null || province.length()<2){
            return -1;
        }
        //取前2个字
        String shortName = province.substring(0,2);

        Integer id = provinces.get(shortName);
        if(id==null && province.startsWith("黑龙江")){
            return 9;
        }
        if(id == null){
            return -1;
        }

        return id.intValue();
    }

    static public String getProvinceName(int id){
        if(!inited){
            initProvinces();
        }
        //总共34个省市自治区
        if(id<1 || id > 34){
            return null;
        }

        return idToProvinces.get(id);
    }

    /**
     * *** 注意 *** 请保持这里的ID和province的对应关系不要变，这个会存到city数据中
     */
    static private void initProvinces(){
        provinces.put("北京", 1);
        provinces.put("上海", 2);
        provinces.put("天津", 3);
        provinces.put("重庆", 4);
        provinces.put("河北", 5);
        provinces.put("山西", 6);
        provinces.put("辽宁", 7);
        provinces.put("吉林", 8);
        provinces.put("黑龙江", 9);
        provinces.put("江苏", 10);
        provinces.put("浙江", 11);
        provinces.put("安徽", 12);
        provinces.put("福建", 13);
        provinces.put("江西", 14);
        provinces.put("山东", 15);
        provinces.put("河南", 16);
        provinces.put("湖北", 17);
        provinces.put("湖南", 18);
        provinces.put("广东", 19);
        provinces.put("海南", 20);
        provinces.put("四川", 21);
        provinces.put("贵州", 22);
        provinces.put("云南", 23);
        provinces.put("陕西", 24);
        provinces.put("甘肃", 25);
        provinces.put("青海", 26);
        provinces.put("内蒙", 27);
        provinces.put("广西", 28);
        provinces.put("西藏", 29);
        provinces.put("宁夏", 30);
        provinces.put("新疆", 31);
        provinces.put("香港", 32);
        provinces.put("澳门", 33);
        provinces.put("台湾", 34);

        idToProvinces.put(1, "北京");
        idToProvinces.put(2,"上海");
        idToProvinces.put(3,"天津");
        idToProvinces.put(4,"重庆");
        idToProvinces.put(5,"河北");
        idToProvinces.put(6,"山西");
        idToProvinces.put(7,"辽宁");
        idToProvinces.put(8,"吉林");
        idToProvinces.put(9,"黑龙江");
        idToProvinces.put(10,"江苏");
        idToProvinces.put(11,"浙江");
        idToProvinces.put(12,"安徽");
        idToProvinces.put(13,"福建");
        idToProvinces.put(14,"江西");
        idToProvinces.put(15,"山东");
        idToProvinces.put(16,"河南");
        idToProvinces.put(17,"湖北");
        idToProvinces.put(18,"湖南");
        idToProvinces.put(19,"广东");
        idToProvinces.put(20,"海南");
        idToProvinces.put(21, "四川");
        idToProvinces.put(22,"贵州");
        idToProvinces.put(23,"云南");
        idToProvinces.put(24,"陕西");
        idToProvinces.put(25,"甘肃");
        idToProvinces.put(26,"青海");
        idToProvinces.put(27,"内蒙");
        idToProvinces.put(28,"广西");
        idToProvinces.put(29,"西藏");
        idToProvinces.put(30,"宁夏");
        idToProvinces.put(31,"新疆");
        idToProvinces.put(32,"香港");
        idToProvinces.put(33,"澳门");
        idToProvinces.put(34,"台湾");

        inited = true;
    }

    private Province(){

    }
}
