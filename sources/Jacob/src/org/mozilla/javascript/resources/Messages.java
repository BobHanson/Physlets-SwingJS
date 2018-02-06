package org.mozilla.javascript.resources;

import java.util.ListResourceBundle;

public class Messages extends ListResourceBundle {
   public Object[][] getContents() {
       return contents;
   }
   public static final Object[][] contents = {
       // LOCALIZE THIS
       //ScriptRuntime
       { "msg.assn.create",  "Assignment to undefined {0} will create a new variable. Add a variable statement at the top level scope to remove this warning." },
       { "msg.prop.not.found",  "Property not found." },
       { "msg.invalid.type",  "Invalid JavaScript value of type {0}" },
       { "msg.primitive.expected",  "Primitive type expected (had {0} instead)" },
       { "msg.null.to.object",  "Cannot convert null to an object." },
       { "msg.undef.to.object",  "Cannot convert undefined to an object." },
       { "msg.cyclic.value",  "Cyclic {0} value not allowed." },
       { "msg.is.not.defined",  "{0} is not defined." },
       { "msg.isnt.function",  "{0} is not a function." },
       { "msg.bad.default.value",  "Object''s getDefaultValue() method returned an object." },
       { "msg.instanceof.not.object",  "Can''t use instanceof on a non-object." },
       { "msg.instanceof.bad.prototype",  "''prototype'' property of {0} is not an object." },
       { "msg.bad.radix",  "illegal radix {0}." },

       // undefined
       { "msg.undefined",  "The undefined value has no properties.." },
       // END "LOCALIZE THIS"
   };
}