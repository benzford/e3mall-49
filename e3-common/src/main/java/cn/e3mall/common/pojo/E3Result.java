/**
 * 
 */
package cn.e3mall.common.pojo;


import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author hasee
 *
 */
public class E3Result implements Serializable {
	private static final ObjectMapper MAPPER = new ObjectMapper();
	private Integer status;
	private String msg;
	private Object data;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getmsg() {
		return msg;
	}

	public void setmsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public E3Result() {
	}

	public E3Result(Integer status, String msg, Object data) {
		this.status = status;
		this.msg = msg;
		this.data = data;
	}

	public static E3Result ok() {
		return new E3Result(200, "OK", null);
	}

	public E3Result(Object data) {
		this.status = 200;
		this.msg = "OK";
		this.data = data;
	}

	public static E3Result ok(Object data) {
		return new E3Result(data);
	}

	public static E3Result build(Integer status, String msg, Object data) {
		return new E3Result(status, msg, data);
	}

	public static E3Result build(Integer status, String msg) {
		return new E3Result(status, msg, null);
	}

	public static E3Result format(String json) {
		try {
			return MAPPER.readValue(json, E3Result.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static E3Result formatToPojo(String jsonData, Class<?> clazz) {
		try {
			if (clazz == null) {
				return MAPPER.readValue(jsonData, E3Result.class);
			}
			JsonNode node = MAPPER.readTree(jsonData);
			JsonNode data = node.get("data");
			Object obj = null;
			if (clazz != null) {
				if (data.isObject()) {
					obj = MAPPER.readValue(data.traverse(), clazz);
				} else if (data.isTextual()) {
					obj = MAPPER.readValue(data.asText(), clazz);
				}
			}
			return build(node.get("status").intValue(), node.get("msg").asText(), obj);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static E3Result formatToList(String jsonData, Class<?> clazz) {
		try {
			JsonNode node = MAPPER.readTree(jsonData);
			JsonNode data = node.get("data");
			Object obj = null;
			if (data.isArray() && data.size() > 0) {
				obj=MAPPER.readValue(data.traverse(), 
						MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
			}
			return build(node.get("status").intValue(), node.get("msg").asText(), obj);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
