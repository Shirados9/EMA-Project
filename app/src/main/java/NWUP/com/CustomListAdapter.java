package NWUP.com;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
//Diese Datei ist mehr oder weniger ein Versuch in dem SCrollview anpassbare Elemente zu erstellen
public class CustomListAdapter extends ArrayAdapter {
    //to reference the Activity
    private final Activity context;

    //to store the list of countries
    private final String[] nameArray;

    //to store the list of countries
    private final String[] infoArray;

    public CustomListAdapter(Activity context, String[] nameArrayParam, String[] infoArrayParam){

        super(context,R.layout.zeitzone_scrollview , nameArrayParam);
        this.context=context;
        this.nameArray = nameArrayParam;
        this.infoArray = infoArrayParam;

    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.zeitzone_scrollview, null,true);

        //this code gets references to objects in the listview_row.xml file
        TextView nameTextField = (TextView) rowView.findViewById(R.id.TextviewOben);
        TextView infoTextField = (TextView) rowView.findViewById(R.id.TextviewUnten);

        //this code sets the values of the objects to values from the arrays
        nameTextField.setText(nameArray[position]);
        infoTextField.setText(infoArray[position]);

        return rowView;

    };
}
