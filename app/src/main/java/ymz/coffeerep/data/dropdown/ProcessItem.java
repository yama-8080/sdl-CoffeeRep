package ymz.coffeerep.data.dropdown;

import android.widget.ArrayAdapter;

public class ProcessItem {

    public enum Process{
        NATURAL(0, "ナチュラル"),
        WASHED(1, "ウォッシュド"),
        SEMIWASHED(2, "セミウォッシュド（パルプドナチュラル）"),
        HONEY(3, "ハニープロセス"),
        SUMATRA(4, "スマトラ式"),
        ANAEROBIC(5, "嫌気性発酵（アナエロビック）"),
        OTHER(6, "その他"),
        ;

        private final int id;
        private final String text;

        private Process(final int id, final String text) {
            this.id = id;
            this.text = text;
        }

        public int getInt() {
            return this.id;
        }

        public String getString() {
            return this.text;
        }

        //search items by id
        public static ProcessItem.Process getType(final int id) {
            ProcessItem.Process[] items = ProcessItem.Process.values();
            for (ProcessItem.Process item : items) {
                if (item.getInt() == id) {
                    return item;
                }
            }
            return null;
        }

        //return the number of items
        public static int getNumber(){
            return ProcessItem.Process.values().length;
        }
    }

    //set items to dropdown list
    public static void setItemsToAdapter(ArrayAdapter<String> adapter){
        int loop = ProcessItem.Process.getNumber();
        for(int i=0; i<loop; i++){
            if(ProcessItem.Process.getType(i) != null)
                adapter.add(ProcessItem.Process.getType(i).getString());
        }
    }
}
