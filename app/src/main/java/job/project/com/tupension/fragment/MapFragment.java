package job.project.com.tupension.fragment;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import job.project.com.tupension.R;
import job.project.com.tupension.parser.PensionGsonParser;
import job.project.com.tupension.pojo.Pension;


public class MapFragment extends Fragment implements OnMapReadyCallback {

    private SupportMapFragment mapFragment;


    public MapFragment() {

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root=inflater.inflate(R.layout.fragment_map, container, false);

        FragmentManager fm = getChildFragmentManager();
        mapFragment = (SupportMapFragment) fm.findFragmentById(R.id.pensionesMap);

        mapFragment = SupportMapFragment.newInstance();
        mapFragment.getMapAsync(this);
        mapFragment.setRetainInstance(true);
        fm.beginTransaction().add(R.id.pensionesMap,mapFragment).commit();




        return root;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);



        LatLng universidad = new LatLng(11.223124, -74.185896);

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        googleMap.setMyLocationEnabled(true);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(universidad, 13));

        try{
            ConnectivityManager manager=(ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info =manager.getActiveNetworkInfo();
            if(info !=null && info.isConnected()){
                JsonTask jTask=new JsonTask(googleMap,getActivity());
                jTask.execute(new URL( "https://dl.dropbox.com/s/e6fa0ncek3dkve7/pensiones.json?dl=0"));

            }else{
                Toast.makeText(getActivity(), "Error de conexión", Toast.LENGTH_LONG).show();
            }


        }catch (MalformedURLException e){
            e.printStackTrace();
        }








    }

    private class JsonTask extends AsyncTask<URL, Void,List<Pension>> {

        private HttpURLConnection connection;
        private Context context;
        private GoogleMap googleMap;



        public JsonTask(GoogleMap googleMap, Context context) {
            this.googleMap = googleMap;
            this.context = context;

        }


        @Override
        protected List<Pension> doInBackground(URL... urls) {
            ArrayList<Pension> pensiones = null;
            try {
                connection = (HttpURLConnection) urls[0].openConnection();
                connection.setConnectTimeout(15000);
                connection.setReadTimeout(10000);
                int statusCode = connection.getResponseCode();
                if (statusCode != 200) {
                    pensiones = new ArrayList<>();
                    pensiones.add(new Pension("Error", "Error", 0,"Error", null, "Error", null, null, null));

                } else {
                    InputStream stream = new BufferedInputStream(connection.getInputStream());
                    PensionGsonParser gsonParser = new PensionGsonParser();
                    pensiones = (ArrayList<Pension>) gsonParser.leerFlujo(stream);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                connection.disconnect();
            }
            return pensiones;
        }

        @Override
        protected void onPostExecute(final List<Pension> pensions) {
            super.onPostExecute(pensions);




            if(pensions==null){
                Toast.makeText(getActivity(),"Erro1",Toast.LENGTH_SHORT).show();

            }else{
                if(googleMap==null){
                    Toast.makeText(getActivity(),"Error Map",Toast.LENGTH_SHORT).show();
                }else{
                    for(Pension p :pensions) {
                        System.out.println("latitud:"+p.getCoordenadas().getLat() +"Longitud: "+p.getCoordenadas().getLon());

                        LatLng latLng = new LatLng(p.getCoordenadas().getLat(),p.getCoordenadas().getLon());
                        googleMap.addMarker(new MarkerOptions().title(p.getNombre()).snippet("Telefono: "+p.getPersona().getTelefono()).position(latLng));



                    }


                }
            }
        }
    }
}
