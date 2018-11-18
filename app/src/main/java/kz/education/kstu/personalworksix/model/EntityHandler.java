package kz.education.kstu.personalworksix.model;

import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class EntityHandler<T extends BaseEntity> {
    private static final String TAG = "ResponseHandler";

    /**
     * Метод десериализации объекта
     * @param json - данные в формате Json
     * @param tClass - тип желаемого объекта
     * @return T
     * @throws JSONException
     */
    public T handleDataResponse(String json, Class<T> tClass) throws JSONException{
        T t = null;
        if (json == null || json.length() == 0){
            return null;
        }
        JSONObject jsonObject = new JSONObject(json);
        if (jsonObject.toString().length() == 0 || jsonObject.toString().equals("[]")){
            return null;
        } else {
            return deserialize(jsonObject.toString(), tClass);
        }
    }

    /**
     * Метод десериализации коллекций
     * @param json - данные в формате Json
     * @param tClass - тип желаемого объекта
     * @return T
     * @throws JSONException
     */
    public List<T> handleMultipleDataResponse(String json, Class<T> tClass) throws JSONException {
        List<T> tList = null;
        JSONArray jsonArray = new JSONArray(json);
        if (jsonArray.length() == 0){
            return null;
        } else {
            tList = new ArrayList<T>();
            for (int i = 0; i < jsonArray.length(); i++){
                tList.add(deserialize(jsonArray.get(i).toString(), tClass));
            }
            return tList;
        }
    }

    /**
     * Метод десериализации объекта
     * @param jsonData - дынные в формате Json
     * @param tClass - переданный тип объекта на выходе
     * @return T
     */
    private T deserialize(String jsonData, Class<T> tClass) {
        T object = null;
        Gson gson = new Gson();
        try{
            object = gson.fromJson(jsonData, tClass);
        } catch (Exception e){
            Log.e("RESPONSE_HANDLER", "EXCEPTION: " + e.toString());
        }
        return object;
    }

    /**
     * Метод сериализации коллекций
     * @param collection - коллекция объектов
     * @return json данные в формате String
     */
    public String serialize(List<T> collection){
        Gson gson = new Gson();
        return gson.toJson(collection, collection.getClass());
    }

    /**
     * Метод сериализации объекта
     * @param entity - объект
     * @return json данные в формате String
     */
    public String serialize(T entity){
        Gson gson = new Gson();
        return gson.toJson(entity, entity.getClass());
    }
}
