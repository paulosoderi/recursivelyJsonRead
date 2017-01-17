package com.psoderi.main;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

public class Teste {

	public static void main(String[] args) throws IOException {
		 
	String dados = "{\"cabecalho\": {\"nomeCliente\": \"Tomas\"},\"detalhes\": {\"idDetalhes\": \"1\",\"endereco\": {\"rua\": \"Teste rua\"},\"sessaoCartao\": {\"tipoCartao\": \"abc\"},\"ativo\": true},\"contador\": 0,\"versao\": 0,\"data\": []}";

		
		JSONObject mainObject = new JSONObject(dados);
		System.out.println(getAllKeys(mainObject));
	}
	
	public static Set<String> getAllKeys(JSONObject json) {
	    return getAllKeys(json, new HashSet<String>());
	}

	public static Set<String> getAllKeys(JSONArray arr) {
	    return getAllKeys(arr, new HashSet<String>());
	}

	private static Set<String> getAllKeys(JSONArray arr, HashSet<String> keys) {
	    for (int i = 0; i < arr.length(); i++) {
	        Object obj = arr.get(i);
	        if (obj instanceof JSONObject){
	        	keys.addAll(getAllKeys(arr.getJSONObject(i)));
	        }
	        if (obj instanceof JSONArray) {
	        	keys.addAll(getAllKeys(arr.getJSONArray(i)));
	        }
	    }
	    return keys;
	}

	private static Set<String> getAllKeys(JSONObject json, HashSet<String> keys) {
	    for (String key : json.keySet()) {
	        Object obj = json.get(key);
	        if (obj instanceof JSONObject) {
	        	keys.addAll(getAllKeys(json.getJSONObject(key)));
	        }
	        if (obj instanceof JSONArray) {
	        	keys.addAll(getAllKeys(json.getJSONArray(key)));
	        }
	    }

	    keys.addAll(json.keySet());
	    return keys;
	}

}
