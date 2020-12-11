package com.example.products.api.model.serializer;

import java.io.IOException;

import org.springframework.boot.jackson.JsonComponent;

import com.example.products.api.model.Product;
import com.example.products.api.model.SerializingExample;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.POJONode;
import com.fasterxml.jackson.databind.node.TextNode;

@JsonComponent
public class CombinedJsonExampleSerializer {

	public static class JsonExampleSerializer extends JsonSerializer<SerializingExample> {

		@Override
		public void serialize(SerializingExample value, JsonGenerator gen, SerializerProvider serializers)
				throws IOException {
			gen.writeStartObject();
			gen.writeStringField("name", value.getName());
			gen.writeObjectField("product", value.getProduct());
			gen.writeEndObject();
		}
	}
	
	public static class JsonExampleDeserializer extends JsonDeserializer<SerializingExample> {

		@Override
		public SerializingExample deserialize(JsonParser p, DeserializationContext ctxt)
				throws IOException, JsonProcessingException {
			TreeNode treeNode = p.getCodec().readTree(p);
			TextNode name = (TextNode) treeNode.get("name");
			ObjectNode product = (ObjectNode) treeNode.get("product");
			ObjectMapper om = new ObjectMapper();
			Product serializedProduct = om.convertValue(product, Product.class);
			return new SerializingExample(name.asText(), serializedProduct);
		}
	}
}
