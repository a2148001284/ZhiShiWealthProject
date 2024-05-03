package com.zhouy.login.util.excel;

import java.util.List;

public class SortUtil {
    /**
     * 使用Long 对12位学号排序
     * @param o1 o1
     * @param o2 o2
     * @param idPosition 学号所在的列号(0起)
     * @return int
     */
    public static int doSort(List<Object> o1, List<Object> o2,Integer idPosition){
//        //System.out.println(o1.get(1).getClass());//Long
//        //按照学号升序 12位学号 取前四位年份+后三位组成排序的新id
//        StringBuilder id_1 = new StringBuilder();
//        StringBuilder id_2 = new StringBuilder();
//        //[)
//        id_1.append(String.valueOf(o1.get(idPosition)).substring(0, 4));
//        id_1.append(String.valueOf(o1.get(idPosition)).substring(9,11));
////        System.out.println("id_1:"+id_1);
//        id_2.append(String.valueOf(o2.get(idPosition)).substring(0, 4));
//        id_2.append(String.valueOf(o2.get(idPosition)).substring(9,11));
////        System.out.println("id_2:"+id_2);
//        return Integer.parseInt(id_1.toString()) - Integer.parseInt(id_2.toString());
        Object tar1 = o1.get(idPosition);
        long l = Long.parseLong(String.valueOf(tar1));

        Object tar2 = o2.get(idPosition);
        long l1 = Long.parseLong(String.valueOf(tar2));

        long l2 = l - l1;
        return Integer.parseInt(String.valueOf(l2));
    }

}
