package com.huuc.json;

import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.NopAnnotationIntrospector;
import com.huuc.vo.GetMsg;

public class AnnotationIntrospector extends NopAnnotationIntrospector {
    @Override
    public Object findSerializer(Annotated am) {
        GetMsg annotation = am.getAnnotation(GetMsg.class);
        if (annotation != null) {
            return GetMsgSerializer.class;
        }
        return null;
    }
}