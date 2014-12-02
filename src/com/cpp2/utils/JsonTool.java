package com.cpp2.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Jsonת��������
 * @author Rose
 */
public class JsonTool
{
	/**
	 * json to list
	 * @param strJson
	 * @return
	 */
	public static List<Map<String, Object>> parseJSON2List(String strJson)
	{
		JSONArray jsonArr = JSONArray.fromObject(strJson);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		/* ����json����, ��json������ӵ�lsit���� */
		Iterator i = jsonArr.iterator();
		while(i.hasNext())
		{
			JSONObject objJson = (JSONObject) i.next();
			list.add(objJson);
		}
		return list;
	}
	
	/**
	 * json to map
	 * @param strJson
	 * @return
	 */
	public static Map<String, Object> parseJSON2Map(String strJson)
	{
		Map<String, Object> map = new HashMap();
		// ��������
		JSONObject json = JSONObject.fromObject(strJson);
		for(Object key : json.keySet())
		{
			Object value = json.get(key);
			/* ���ڲ㻹������Ļ�,,�������� */
			if(value instanceof JSONArray)
			{
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				Iterator<JSONObject> i = ((JSONArray) value).iterator();
				while(i.hasNext())
				{
					JSONObject objJson = i.next();
					list.add(parseJSON2Map(objJson.toString()));
				}
				map.put(key.toString(), list);
			}
			else
			{
				map.put(key.toString(), value);
			}
		}
		return map;
	}
	
	/**
	 * map to json
	 * @param map
	 * @return
	 */
	public static String map2Json(Map<String, String> map)
	{
		Set<String> keys = map.keySet();						// ��ȡ���м�����
		String key = "";
		String value = "";
		
		StringBuffer bufJson = new StringBuffer();
		bufJson.append("{");
		for(Iterator<String> i = keys.iterator(); i.hasNext();)
		{
			key = i.next();
			value = map.get(key);
			bufJson.append(key+":"+"\""+value+"\"");
			if(i.hasNext())
			{
				bufJson.append(",");
			}
		}
		bufJson.append("}");
		return null;
	}
}
