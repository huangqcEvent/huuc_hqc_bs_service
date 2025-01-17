package com.huuc.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.huuc.enums.BaseEnum;
import com.huuc.vo.GetMsg;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

@Getter
@Component
public class GetMsgSerializer extends StdSerializer<Object> implements ContextualSerializer {
    private static final long serialVersionUID = 1L;

    private Class<? extends BaseEnum> enumClass;
    private String fieldName;

    public GetMsgSerializer() {
        super(Object.class);
    }

    public GetMsgSerializer(Class<? extends BaseEnum> enumClass, String fieldName) {
        super(Object.class);
        this.enumClass = enumClass;
        this.fieldName = fieldName;
    }

    @Override
    public void serialize(Object value, JsonGenerator gen, SerializerProvider provider) throws IOException {

        gen.writeObject(value);
        gen.writeFieldName(fieldName + "Name");

        if (Objects.isNull(value)) {
            gen.writeString("");
            return;
        }
        // 获取枚举类的所有枚举常量
        BaseEnum[] enumConstants = enumClass.getEnumConstants();
        if (enumConstants == null) {
            throw new IllegalArgumentException("Enum class does not have any constants");
        }
        // 查找匹配的枚举常量
        Optional<BaseEnum> matchingEnum = Arrays.stream(enumConstants)
                .filter(e -> e.getCode().equals(value))
                .findFirst();
        if (matchingEnum.isPresent()) {
            BaseEnum baseEnum = matchingEnum.get();
            String msg = baseEnum.getMsg();
            gen.writeString(msg);
        } else {
            gen.writeString("Unknown");
        }
    }




    @Override
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) {
        GetMsg annotation = beanProperty.getAnnotation(GetMsg.class);
        String fieldName = beanProperty.getName();
        return new GetMsgSerializer(annotation.enumClass(), fieldName);
    }
}
