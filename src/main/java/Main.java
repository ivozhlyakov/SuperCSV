import au.com.bytecode.opencsv.CSVWriter;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.constraint.UniqueHashCode;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.lang.annotation.Annotation;
import java.util.*;

/**
 * Created by ivozh on 05.04.2018.
 */
public class Main {

    public static List<Map<String, Object>> defList() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (int i=1; i<5; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("ID", i+"");
            map.put("NAME", "name"+i);
            map.put("ROLE", "role"+i);
            map.put("SALARY", "salary"+i);
            list.add(map);
        }
        return list;
    }

    public static void main(String[] args) throws IOException {
        List<Employee> employees = generateData(defList());

        StringWriter writer = new StringWriter();

        // создаем CsvBeanWriter со стандартными настройками (кодировка, переносы строк, разделители и т.д.)
        ICsvBeanWriter csvBeanWriter = new CsvBeanWriter(new FileWriter("test.csv"), CsvPreference.STANDARD_PREFERENCE);
        Set<String> set = defList().get(0).keySet();
        String[] header = set.toArray(new String[set.size()]);// new String[]{"id", "name", "role", "salary"};

        // создаем заголовок
        csvBeanWriter.writeHeader(header);

        for (Employee employee : employees) {
            csvBeanWriter.write(employee, header, getProcessors());
        }

        csvBeanWriter.close();

        System.out.println(writer.toString());
    }

    private static CellProcessor[] getProcessors() {
        return new CellProcessor[]{
                new UniqueHashCode(),
                new NotNull(),
                new Optional(),
                new Optional()
        };
    }

    private static List<Employee> generateData(List<Map<String, Object>> list) {
        List<Employee> employees = new ArrayList<Employee>();
        for (Map<String, Object> map : list) {
            Employee employee = new Employee();
            employee.setId((String) map.get("ID"));
            employee.setName((String) map.get("NAME"));
            employee.setRole((String) map.get("ROLE"));
            employee.setSalary((String) map.get("SALARY"));
            employees.add(employee);
        }
        return employees;
    }
   /* public static void main(String[] args) throws Exception
    {
        String csv = "data.csv";
        CSVWriter writer = new CSVWriter(new FileWriter(csv, true));
        String [] record = "4,David,Feezor,USA,40".split(",");
        writer.writeNext(record);
        writer.close();
    }
*/
    public static void dfg(String[] args) throws IOException {
        CSVWriter csvWriter = new CSVWriter(new FileWriter("test.csv", true));

    }

    //public List<String> set
}
