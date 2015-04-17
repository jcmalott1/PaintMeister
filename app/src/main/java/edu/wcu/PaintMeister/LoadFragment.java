package edu.wcu.PaintMeister;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

//########################################################################
/**
 * 
 * @author Andrew
 * 
 * A list of places that the user can select.
 *
 */
//########################################################################
public class LoadFragment extends Fragment implements OnItemClickListener {

	/**An array of place names**/
	private  final String[] VALUES = new String[] { "Cullowhee", "London", "Oxford", "Cardiff", "Abercynon","Sylva","Dilsboro" };


	
	//=====================================================================
	/**
	 * Called to create the fragment view.
	 */
	//=====================================================================
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_place_list , container, false);
        
        //Set the list view adapters for this view.
        ListView lv = (ListView) rootView.findViewById(R.id.list);
        
        //this class will be the on item click listener for the list.
        lv.setOnItemClickListener(this);

        //Instantiate the custome addapter and pass to it the lyout yo br displayed
     	ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), R.layout.list_item, VALUES);
     	
     	lv.setAdapter(adapter);
        
        return rootView;
    }//===================================================================
	

	//======================================================================
	 /*
	 When an item in the list is clicked this is fired.
	 */
	//=======================================================================
	@Override
	public void onItemClick(AdapterView<?> l, View view, int position, long id) {
		
		// When clicked, show a toast with the TextView text
	      Toast.makeText(this.getActivity().getApplicationContext(), ((TextView) view).getText(),
	                                                                    Toast.LENGTH_SHORT).show();
	      	      

		
	}//end onItemClick========================================================
	
	




}//end class##################################################################
