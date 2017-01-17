package com.psoderi.main;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

public class Teste2 {

	public static void main(String[] args) throws IOException {
		String dados = "{\"cabecalho\": {\"nomeCliente\": \"Tomas\"},\"detalhes\": {\"idDetalhes\": \"1\",\"endereco\": {\"rua\": \"Teste rua\"},\"sessaoCartao\": {\"tipoCartao\": \"abc\"},\"ativo\": true},\"contador\": 0,\"versao\": 0,\"data\": []}"; 
				
		JSONObject mainObject = new JSONObject(dados);
		System.out.println(getAllKeys(mainObject, null));
	}
	
	public static Set<String> getAllKeys(JSONObject json, String _pai) {
	    return getAllKeys(json, new HashSet<String>(), _pai);
	}

	public static Set<String> getAllKeys(JSONArray arr, String _pai) {
	    return getAllKeys(arr, new HashSet<String>(), _pai);
	}

	private static Set<String> getAllKeys(JSONArray arr, HashSet<String> keys, String _pai) {
	    for (int i = 0; i < arr.length(); i++) {
	        Object obj = arr.get(i);
	        if (obj instanceof JSONObject){
	        	keys.addAll(getAllKeys(arr.getJSONObject(i), _pai));
	        }
	        if (obj instanceof JSONArray) {
	        	keys.addAll(getAllKeys(arr.getJSONArray(i), _pai));
	        }
	    }
	    return keys;
	}
	
	private static Set<String> getAllKeys(JSONObject json, HashSet<String> keys, String _pai) {
		HashSet<String> chavesAdicionadas = new HashSet<String>();
		for (String key : json.keySet()) {
	        Object obj = json.get(key);
	        String pai;
	        if(_pai != null){
	        	pai = _pai + "." + key;
	        }else{
	        	pai = key;
	        }

	        if (obj instanceof JSONObject) {
	        	chavesAdicionadas.addAll(getAllKeys(json.getJSONObject(key), pai));
	        }
	        if (obj instanceof JSONArray) {
	        	chavesAdicionadas.addAll(getAllKeys(json.getJSONArray(key), pai));
	        }
//	        System.out.println("Pai: " + pai);
	        if(!(obj instanceof JSONObject) && !(obj instanceof JSONArray)){
	        	chavesAdicionadas.add(pai);
	        }
	    }

	    return chavesAdicionadas;
	}

}
