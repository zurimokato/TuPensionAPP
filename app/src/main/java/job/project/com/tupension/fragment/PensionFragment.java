package job.project.com.tupension.fragment;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import job.project.com.tupension.R;
import job.project.com.tupension.adapters.PensionRescyclerViewAdapter;
import job.project.com.tupension.parser.PensionGsonParser;
import job.project.com.tupension.pojo.Pension;


public class PensionFragment extends Fragment {


    private PensionRescyclerViewAdapter pAdapter;
    private RecyclerView recyView;


    public PensionFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_pension, container, false);

        recyView=(RecyclerView) view.findViewById(R.id.recyclePes);
        recyView.setLayoutManager(new LinearLayoutManager(getActivity()));

        try{
            ConnectivityManager manager=(ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info =manager.getActiveNetworkInfo();
            if(info !=null && info.isConnected()){
                JsonTask jTask=new JsonTask(recyView,getActivity());
                jTask.execute(new URL("https://dl.dropbox.com/s/e6fa0ncek3dkve7/pensiones.json?dl=0"));
            }else{
                Toast.makeText(getActivity(), "Error de conexión", Toast.LENGTH_LONG).show();
            }


        }catch (MalformedURLException e){
            e.printStackTrace();
        }

        return view;
    }


    private class JsonTask extends AsyncTask<URL, Void,List<Pension>>{

        private HttpURLConnection connection;
        private Context context;
        private RecyclerView recyclerP;
        private PensionRescyclerViewAdapter pAdapter;


        public JsonTask(RecyclerView recycler,Context context){
            this.recyclerP=recycler;
            this.context=context;

        }


        @Override
        protected List<Pension> doInBackground(URL... urls) {
            ArrayList<Pension>pensiones=null;
            try {
                connection=(HttpURLConnection) urls[0].openConnection();
                connection.setConnectTimeout(15000);
                connection.setReadTimeout(10000);
                int statusCode=connection.getResponseCode();
                if (statusCode!=200){
                    pensiones=new ArrayList<>();
                    pensiones.add(new Pension("Error","Error",0,"Error",null,"Error",null,null, null));

                }else{
                    InputStream stream= new BufferedInputStream(connection.getInputStream());
                    PensionGsonParser gsonParser=new PensionGsonParser();
                    pensiones= (ArrayList<Pension>) gsonParser.leerFlujo(stream);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                connection.disconnect();
            }
            return pensiones;
        }

        @Override
        protected void onPostExecute(List<Pension> pensiones) {
            super.onPostExecute(pensiones);
            if(pensiones!=null){
                recyView.setLayoutManager(new LinearLayoutManager(context));
                pAdapter=new PensionRescyclerViewAdapter(pensiones,getActivity());
                recyclerP.setAdapter(pAdapter);

            }else{
                Toast.makeText(context,"Ocurrio un error al parsear el Json", Toast.LENGTH_SHORT).show();
            }

        }
    }




}
