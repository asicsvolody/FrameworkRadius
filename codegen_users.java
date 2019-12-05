// ORM class for table 'users'
// WARNING: This class is AUTO-GENERATED. Modify at your own risk.
//
// Debug information:
// Generated date: Thu Dec 05 13:54:02 MSK 2019
// For connector: org.apache.sqoop.manager.MySQLManager
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapred.lib.db.DBWritable;
import com.cloudera.sqoop.lib.JdbcWritableBridge;
import com.cloudera.sqoop.lib.DelimiterSet;
import com.cloudera.sqoop.lib.FieldFormatter;
import com.cloudera.sqoop.lib.RecordParser;
import com.cloudera.sqoop.lib.BooleanParser;
import com.cloudera.sqoop.lib.BlobRef;
import com.cloudera.sqoop.lib.ClobRef;
import com.cloudera.sqoop.lib.LargeObjectLoader;
import com.cloudera.sqoop.lib.SqoopRecord;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class codegen_users extends SqoopRecord  implements DBWritable, Writable {
  private final int PROTOCOL_VERSION = 3;
  public int getClassFormatVersion() { return PROTOCOL_VERSION; }
  protected ResultSet __cur_result_set;
  private Integer user_id;
  public Integer get_user_id() {
    return user_id;
  }
  public void set_user_id(Integer user_id) {
    this.user_id = user_id;
  }
  public codegen_users with_user_id(Integer user_id) {
    this.user_id = user_id;
    return this;
  }
  private String user_name;
  public String get_user_name() {
    return user_name;
  }
  public void set_user_name(String user_name) {
    this.user_name = user_name;
  }
  public codegen_users with_user_name(String user_name) {
    this.user_name = user_name;
    return this;
  }
  private String user_phone;
  public String get_user_phone() {
    return user_phone;
  }
  public void set_user_phone(String user_phone) {
    this.user_phone = user_phone;
  }
  public codegen_users with_user_phone(String user_phone) {
    this.user_phone = user_phone;
    return this;
  }
  private Integer user_age;
  public Integer get_user_age() {
    return user_age;
  }
  public void set_user_age(Integer user_age) {
    this.user_age = user_age;
  }
  public codegen_users with_user_age(Integer user_age) {
    this.user_age = user_age;
    return this;
  }
  private Integer user_marital_status;
  public Integer get_user_marital_status() {
    return user_marital_status;
  }
  public void set_user_marital_status(Integer user_marital_status) {
    this.user_marital_status = user_marital_status;
  }
  public codegen_users with_user_marital_status(Integer user_marital_status) {
    this.user_marital_status = user_marital_status;
    return this;
  }
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof codegen_users)) {
      return false;
    }
    codegen_users that = (codegen_users) o;
    boolean equal = true;
    equal = equal && (this.user_id == null ? that.user_id == null : this.user_id.equals(that.user_id));
    equal = equal && (this.user_name == null ? that.user_name == null : this.user_name.equals(that.user_name));
    equal = equal && (this.user_phone == null ? that.user_phone == null : this.user_phone.equals(that.user_phone));
    equal = equal && (this.user_age == null ? that.user_age == null : this.user_age.equals(that.user_age));
    equal = equal && (this.user_marital_status == null ? that.user_marital_status == null : this.user_marital_status.equals(that.user_marital_status));
    return equal;
  }
  public boolean equals0(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof codegen_users)) {
      return false;
    }
    codegen_users that = (codegen_users) o;
    boolean equal = true;
    equal = equal && (this.user_id == null ? that.user_id == null : this.user_id.equals(that.user_id));
    equal = equal && (this.user_name == null ? that.user_name == null : this.user_name.equals(that.user_name));
    equal = equal && (this.user_phone == null ? that.user_phone == null : this.user_phone.equals(that.user_phone));
    equal = equal && (this.user_age == null ? that.user_age == null : this.user_age.equals(that.user_age));
    equal = equal && (this.user_marital_status == null ? that.user_marital_status == null : this.user_marital_status.equals(that.user_marital_status));
    return equal;
  }
  public void readFields(ResultSet __dbResults) throws SQLException {
    this.__cur_result_set = __dbResults;
    this.user_id = JdbcWritableBridge.readInteger(1, __dbResults);
    this.user_name = JdbcWritableBridge.readString(2, __dbResults);
    this.user_phone = JdbcWritableBridge.readString(3, __dbResults);
    this.user_age = JdbcWritableBridge.readInteger(4, __dbResults);
    this.user_marital_status = JdbcWritableBridge.readInteger(5, __dbResults);
  }
  public void readFields0(ResultSet __dbResults) throws SQLException {
    this.user_id = JdbcWritableBridge.readInteger(1, __dbResults);
    this.user_name = JdbcWritableBridge.readString(2, __dbResults);
    this.user_phone = JdbcWritableBridge.readString(3, __dbResults);
    this.user_age = JdbcWritableBridge.readInteger(4, __dbResults);
    this.user_marital_status = JdbcWritableBridge.readInteger(5, __dbResults);
  }
  public void loadLargeObjects(LargeObjectLoader __loader)
      throws SQLException, IOException, InterruptedException {
  }
  public void loadLargeObjects0(LargeObjectLoader __loader)
      throws SQLException, IOException, InterruptedException {
  }
  public void write(PreparedStatement __dbStmt) throws SQLException {
    write(__dbStmt, 0);
  }

  public int write(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeInteger(user_id, 1 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeString(user_name, 2 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(user_phone, 3 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeInteger(user_age, 4 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(user_marital_status, 5 + __off, -6, __dbStmt);
    return 5;
  }
  public void write0(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeInteger(user_id, 1 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeString(user_name, 2 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(user_phone, 3 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeInteger(user_age, 4 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(user_marital_status, 5 + __off, -6, __dbStmt);
  }
  public void readFields(DataInput __dataIn) throws IOException {
this.readFields0(__dataIn);  }
  public void readFields0(DataInput __dataIn) throws IOException {
    if (__dataIn.readBoolean()) { 
        this.user_id = null;
    } else {
    this.user_id = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.user_name = null;
    } else {
    this.user_name = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.user_phone = null;
    } else {
    this.user_phone = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.user_age = null;
    } else {
    this.user_age = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.user_marital_status = null;
    } else {
    this.user_marital_status = Integer.valueOf(__dataIn.readInt());
    }
  }
  public void write(DataOutput __dataOut) throws IOException {
    if (null == this.user_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.user_id);
    }
    if (null == this.user_name) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, user_name);
    }
    if (null == this.user_phone) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, user_phone);
    }
    if (null == this.user_age) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.user_age);
    }
    if (null == this.user_marital_status) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.user_marital_status);
    }
  }
  public void write0(DataOutput __dataOut) throws IOException {
    if (null == this.user_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.user_id);
    }
    if (null == this.user_name) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, user_name);
    }
    if (null == this.user_phone) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, user_phone);
    }
    if (null == this.user_age) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.user_age);
    }
    if (null == this.user_marital_status) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.user_marital_status);
    }
  }
  private static final DelimiterSet __outputDelimiters = new DelimiterSet((char) 44, (char) 10, (char) 0, (char) 0, false);
  public String toString() {
    return toString(__outputDelimiters, true);
  }
  public String toString(DelimiterSet delimiters) {
    return toString(delimiters, true);
  }
  public String toString(boolean useRecordDelim) {
    return toString(__outputDelimiters, useRecordDelim);
  }
  public String toString(DelimiterSet delimiters, boolean useRecordDelim) {
    StringBuilder __sb = new StringBuilder();
    char fieldDelim = delimiters.getFieldsTerminatedBy();
    __sb.append(FieldFormatter.escapeAndEnclose(user_id==null?"null":"" + user_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(user_name==null?"null":user_name, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(user_phone==null?"null":user_phone, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(user_age==null?"null":"" + user_age, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(user_marital_status==null?"null":"" + user_marital_status, delimiters));
    if (useRecordDelim) {
      __sb.append(delimiters.getLinesTerminatedBy());
    }
    return __sb.toString();
  }
  public void toString0(DelimiterSet delimiters, StringBuilder __sb, char fieldDelim) {
    __sb.append(FieldFormatter.escapeAndEnclose(user_id==null?"null":"" + user_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(user_name==null?"null":user_name, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(user_phone==null?"null":user_phone, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(user_age==null?"null":"" + user_age, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(user_marital_status==null?"null":"" + user_marital_status, delimiters));
  }
  private static final DelimiterSet __inputDelimiters = new DelimiterSet((char) 44, (char) 10, (char) 0, (char) 0, false);
  private RecordParser __parser;
  public void parse(Text __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(CharSequence __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(byte [] __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(char [] __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(ByteBuffer __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(CharBuffer __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  private void __loadFromFields(List<String> fields) {
    Iterator<String> __it = fields.listIterator();
    String __cur_str = null;
    try {
    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.user_id = null; } else {
      this.user_id = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.user_name = null; } else {
      this.user_name = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.user_phone = null; } else {
      this.user_phone = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.user_age = null; } else {
      this.user_age = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.user_marital_status = null; } else {
      this.user_marital_status = Integer.valueOf(__cur_str);
    }

    } catch (RuntimeException e) {    throw new RuntimeException("Can't parse input data: '" + __cur_str + "'", e);    }  }

  private void __loadFromFields0(Iterator<String> __it) {
    String __cur_str = null;
    try {
    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.user_id = null; } else {
      this.user_id = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.user_name = null; } else {
      this.user_name = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.user_phone = null; } else {
      this.user_phone = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.user_age = null; } else {
      this.user_age = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.user_marital_status = null; } else {
      this.user_marital_status = Integer.valueOf(__cur_str);
    }

    } catch (RuntimeException e) {    throw new RuntimeException("Can't parse input data: '" + __cur_str + "'", e);    }  }

  public Object clone() throws CloneNotSupportedException {
    codegen_users o = (codegen_users) super.clone();
    return o;
  }

  public void clone0(codegen_users o) throws CloneNotSupportedException {
  }

  public Map<String, Object> getFieldMap() {
    Map<String, Object> __sqoop$field_map = new TreeMap<String, Object>();
    __sqoop$field_map.put("user_id", this.user_id);
    __sqoop$field_map.put("user_name", this.user_name);
    __sqoop$field_map.put("user_phone", this.user_phone);
    __sqoop$field_map.put("user_age", this.user_age);
    __sqoop$field_map.put("user_marital_status", this.user_marital_status);
    return __sqoop$field_map;
  }

  public void getFieldMap0(Map<String, Object> __sqoop$field_map) {
    __sqoop$field_map.put("user_id", this.user_id);
    __sqoop$field_map.put("user_name", this.user_name);
    __sqoop$field_map.put("user_phone", this.user_phone);
    __sqoop$field_map.put("user_age", this.user_age);
    __sqoop$field_map.put("user_marital_status", this.user_marital_status);
  }

  public void setField(String __fieldName, Object __fieldVal) {
    if ("user_id".equals(__fieldName)) {
      this.user_id = (Integer) __fieldVal;
    }
    else    if ("user_name".equals(__fieldName)) {
      this.user_name = (String) __fieldVal;
    }
    else    if ("user_phone".equals(__fieldName)) {
      this.user_phone = (String) __fieldVal;
    }
    else    if ("user_age".equals(__fieldName)) {
      this.user_age = (Integer) __fieldVal;
    }
    else    if ("user_marital_status".equals(__fieldName)) {
      this.user_marital_status = (Integer) __fieldVal;
    }
    else {
      throw new RuntimeException("No such field: " + __fieldName);
    }
  }
  public boolean setField0(String __fieldName, Object __fieldVal) {
    if ("user_id".equals(__fieldName)) {
      this.user_id = (Integer) __fieldVal;
      return true;
    }
    else    if ("user_name".equals(__fieldName)) {
      this.user_name = (String) __fieldVal;
      return true;
    }
    else    if ("user_phone".equals(__fieldName)) {
      this.user_phone = (String) __fieldVal;
      return true;
    }
    else    if ("user_age".equals(__fieldName)) {
      this.user_age = (Integer) __fieldVal;
      return true;
    }
    else    if ("user_marital_status".equals(__fieldName)) {
      this.user_marital_status = (Integer) __fieldVal;
      return true;
    }
    else {
      return false;    }
  }
}
