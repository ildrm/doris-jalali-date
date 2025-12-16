
# Doris Jalali Date UDFs

**doris-jalali-date** is a collection of Java-based User Defined Functions (UDFs) designed for Apache Doris to handle Persian (Jalali) calendar date conversions and operations. These UDFs enable converting Jalali dates to Gregorian, extracting Jalali date components, and performing basic arithmetic operations related to dates, enhancing Doris SQL query capabilities for Persian date processing.

---

## Features

- Convert Jalali date strings to Gregorian date strings
- Extract Jalali year, month, month name, and day from Jalali date strings
- Convert Gregorian dates back to Jalali
- Support custom modulus and division functions used internally for date calculations
- Provide arrays and helpers for Jalali/Gregorian date month day mappings

---

## Supported Functions

| Function Name | Input Types           | Return Type | Description                            |
|---------------|----------------------|-------------|------------------------------------|
| `pDate`       | `STRING`             | `STRING`    | Convert Jalali date string to Gregorian date string |
| `pYear`       | `STRING`             | `INT`       | Extract Jalali year from a Jalali date string |
| `pMonth`      | `STRING`             | `INT`       | Extract Jalali month from a Jalali date string |
| `pMonthName`  | `STRING`             | `STRING`    | Extract Jalali month name from a Jalali date string |
| `pDay`        | `STRING`             | `INT`       | Extract Jalali day from a Jalali date string |
| `myMod`       | `INT, INT`           | `BIGINT`    | Custom modulus function for integer inputs |
| `gdmArray`    | `SMALLINT`           | `SMALLINT`  | Gregorian date month day array helper |
| `gDate`       | `SMALLINT, SMALLINT, SMALLINT` | `STRING` | Convert Gregorian year, month, day to Jalali date string |
| `gDateStr`    | `STRING`             | `STRING`    | Convert Gregorian date string to Jalali date string |
| `jdmArray`    | `SMALLINT`           | `SMALLINT`  | Jalali date month day array helper |
| `myDiv`       | `INT, INT`           | `BIGINT`    | Custom division function for integer inputs |

---

## Installation

### 1. Place the JAR file

Copy the `jalali-date.jar` file to a directory accessible by Doris FE and BE nodes, for example:

```bash
/opt/apache-doris/udf/jalali-date.jar
```

### 2. Register the functions in Doris

Use the following SQL commands to create the UDFs in Doris. Adjust the file path if necessary.

#### Doris Config

```sql
CREATE FUNCTION pDate(STRING) RETURNS STRING
PROPERTIES (
"file"="file:///opt/apache-doris/udf/jalali-date.jar",
"symbol"="com.ildrm.jalali.PDateUDF",
"type"="JAVA_UDF"
);

CREATE FUNCTION pYear(STRING) RETURNS INT
PROPERTIES (
"file"="file:///opt/apache-doris/udf/jalali-date.jar",
"symbol"="com.ildrm.jalali.PYearUDF",
"type"="JAVA_UDF"
);

CREATE FUNCTION pMonth(STRING) RETURNS INT
PROPERTIES (
"file"="file:///opt/apache-doris/udf/jalali-date.jar",
"symbol"="com.ildrm.jalali.PMonthUDF",
"type"="JAVA_UDF"
);

CREATE FUNCTION pMonthName(STRING) RETURNS STRING
PROPERTIES (
"file"="file:///opt/apache-doris/udf/jalali-date.jar",
"symbol"="com.ildrm.jalali.PMonthNameUDF",
"type"="JAVA_UDF"
);

CREATE FUNCTION pDay(STRING) RETURNS INT
PROPERTIES (
"file"="file:///opt/apache-doris/udf/jalali-date.jar",
"symbol"="com.ildrm.jalali.PDayUDF",
"type"="JAVA_UDF"
);

CREATE FUNCTION myMod(INT, INT) RETURNS BIGINT
PROPERTIES (
"file"="file:///opt/apache-doris/udf/jalali-date.jar",
"symbol"="com.ildrm.jalali.MyModUDF",
"type"="JAVA_UDF"
);

CREATE FUNCTION gdmArray(SMALLINT) RETURNS SMALLINT
PROPERTIES (
"file"="file:///opt/apache-doris/udf/jalali-date.jar",
"symbol"="com.ildrm.jalali.GdmArrayUDF",
"type"="JAVA_UDF"
);

CREATE FUNCTION gDate(SMALLINT, SMALLINT, SMALLINT) RETURNS STRING
PROPERTIES (
"file"="file:///opt/apache-doris/udf/jalali-date.jar",
"symbol"="com.ildrm.jalali.GDateUDF",
"type"="JAVA_UDF"
);

CREATE FUNCTION gDateStr(STRING) RETURNS STRING
PROPERTIES (
"file"="file:///opt/apache-doris/udf/jalali-date.jar",
"symbol"="com.ildrm.jalali.GDateStrUDF",
"type"="JAVA_UDF"
);

CREATE FUNCTION jdmArray(SMALLINT) RETURNS SMALLINT
PROPERTIES (
"file"="file:///opt/apache-doris/udf/jalali-date.jar",
"symbol"="com.ildrm.jalali.JdmArrayUDF",
"type"="JAVA_UDF"
);

CREATE FUNCTION myDiv(INT, INT) RETURNS BIGINT
PROPERTIES (
"file"="file:///opt/apache-doris/udf/jalali-date.jar",
"symbol"="com.ildrm.jalali.MyDivUDF",
"type"="JAVA_UDF"
);
```

#### StarRocks
```sql
CREATE FUNCTION pDate(STRING) RETURNS STRING
PROPERTIES (
"file"="file:///opt/starrocks/udf/jalali-date.jar",
"symbol"="com.ildrm.jalali.PDateUDF",
"type"="StarrocksJar"
);

CREATE FUNCTION pYear(STRING) RETURNS INT
PROPERTIES (
"file"="file:///opt/starrocks/udf/jalali-date.jar",
"symbol"="com.ildrm.jalali.PYearUDF",
"type"="StarrocksJar"
);

CREATE FUNCTION pMonth(STRING) RETURNS INT
PROPERTIES (
"file"="file:///opt/starrocks/udf/jalali-date.jar",
"symbol"="com.ildrm.jalali.PMonthUDF",
"type"="StarrocksJar"
);

CREATE FUNCTION pMonthName(STRING) RETURNS STRING
PROPERTIES (
"file"="file:///opt/starrocks/udf/jalali-date.jar",
"symbol"="com.ildrm.jalali.PMonthNameUDF",
"type"="StarrocksJar"
);

CREATE FUNCTION pDay(STRING) RETURNS INT
PROPERTIES (
"file"="file:///opt/starrocks/udf/jalali-date.jar",
"symbol"="com.ildrm.jalali.PDayUDF",
"type"="StarrocksJar"
);

CREATE FUNCTION myMod(INT, INT) RETURNS BIGINT
PROPERTIES (
"file"="file:///opt/starrocks/udf/jalali-date.jar",
"symbol"="com.ildrm.jalali.MyModUDF",
"type"="StarrocksJar"
);

CREATE FUNCTION gdmArray(SMALLINT) RETURNS SMALLINT
PROPERTIES (
"file"="file:///opt/starrocks/udf/jalali-date.jar",
"symbol"="com.ildrm.jalali.GdmArrayUDF",
"type"="StarrocksJar"
);

CREATE FUNCTION gDate(SMALLINT, SMALLINT, SMALLINT) RETURNS STRING
PROPERTIES (
"file"="file:///opt/starrocks/udf/jalali-date.jar",
"symbol"="com.ildrm.jalali.GDateUDF",
"type"="StarrocksJar"
);

CREATE FUNCTION gDateStr(STRING) RETURNS STRING
PROPERTIES (
"file"="file:///opt/starrocks/udf/jalali-date.jar",
"symbol"="com.ildrm.jalali.GDateStrUDF",
"type"="StarrocksJar"
);

CREATE FUNCTION jdmArray(SMALLINT) RETURNS SMALLINT
PROPERTIES (
"file"="file:///opt/starrocks/udf/jalali-date.jar",
"symbol"="com.ildrm.jalali.JdmArrayUDF",
"type"="StarrocksJar"
);

CREATE FUNCTION myDiv(INT, INT) RETURNS BIGINT
PROPERTIES (
"file"="file:///opt/starrocks/udf/jalali-date.jar",
"symbol"="com.ildrm.jalali.MyDivUDF",
"type"="StarrocksJar"
);
```

---

## Uninstall / Remove Functions

To remove the UDFs from Doris, run the following DROP FUNCTION commands:

```sql
DROP FUNCTION pDate(STRING);
DROP FUNCTION pYear(STRING);
DROP FUNCTION pMonth(STRING);
DROP FUNCTION pMonthName(STRING);
DROP FUNCTION pDay(STRING);
DROP FUNCTION myMod(INT, INT);
DROP FUNCTION gdmArray(SMALLINT);
DROP FUNCTION gDate(SMALLINT, SMALLINT, SMALLINT);
DROP FUNCTION gDateStr(STRING);
DROP FUNCTION jdmArray(SMALLINT);
DROP FUNCTION myDiv(INT, INT);
```

---

## Usage Examples

```sql
SELECT pDate(now()); -- Returns 1404-05-20 07:38:11

SELECT pYear(now()); -- Returns 1404

SELECT pMonthName(now()); -- Returns مرداد
```

---

## Development & Build

- The source code is implemented in Java.
- Uses standard UDF interfaces compatible with Apache Doris.
- Build the project with your preferred Java build tool (e.g. Maven or Gradle).
- Output JAR must be deployed to Doris nodes and registered via `CREATE FUNCTION`.

---

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

## Contact & Support

For issues, feature requests, or contributions, please open an issue or pull request on the GitHub repository:  
https://github.com/ildrm/doris-jalali-date

---

## Credits

The SQL script `pdate-v2.sql` used as a reference in this project is sourced from the [PersianDate4MySQL](https://github.com/zoghal/PersianDate4MySQL) repository.
