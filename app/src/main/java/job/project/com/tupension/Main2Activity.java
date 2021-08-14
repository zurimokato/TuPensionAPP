package job.project.com.tupension;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.adroitandroid.chipcloud.ChipCloud;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import job.project.com.tupension.pojo.Pension;
import job.project.com.tupension.pojo.Plan;

public class Main2Activity extends AppCompatActivity implements OnMapReadyCallback {
    private TextView textNombreP;
    private TextView textDescripP;
    private TextView textCalifc;
    private TextView textTipoP;
    private RatingBar calificP;
    private ChipCloud chipCloud;
    private TextView textTipoP2;
    private ChipCloud chipCloud2;
    private RelativeLayout linearLayout;
    private TextView textPrecioNum;
    private TextView textPrecioNum2;
    private TextView textDireccion;
    private ImageView imageP;
    private SupportMapFragment mapFragment;
    private GoogleMap mMap;
    private Pension pe;
    private TextView nombrePer;
    private TextView correoPer;
    private TextView personNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.pensionesUbMap);
        textNombreP=(TextView)findViewById(R.id.nomP);
        textDescripP=(TextView)findViewById(R.id.textDescripcion);
        textCalifc=(TextView)findViewById(R.id.textCalific);
        textTipoP=(TextView)findViewById(R.id.textTipo);
        textTipoP2=(TextView)findViewById(R.id.textTipo2);
        calificP=(RatingBar)findViewById(R.id.ratingCalif);
        chipCloud=(ChipCloud)findViewById(R.id.chips);
        chipCloud2=(ChipCloud)findViewById(R.id.chips2);
        linearLayout=(RelativeLayout)findViewById(R.id.linear);
        imageP=(ImageView)findViewById(R.id.imageP);
        textPrecioNum=(TextView)findViewById(R.id.txtPrecioNum);
        textPrecioNum2=(TextView)findViewById(R.id.txtPrecioNum2);
        textDireccion=(TextView)findViewById(R.id.direccionPe);
        nombrePer=(TextView)findViewById(R.id.person_name);
        correoPer=(TextView)findViewById(R.id.person_email);
        personNumber=(TextView)findViewById(R.id.person_number);

        mapFragment.getMapAsync(this);

        setSupportActionBar(toolbar);

        Bundle resiveP=getIntent().getExtras();

        pe= (Pension) resiveP.get("pension");

        if(pe==null){
            Toast.makeText(getBaseContext(),"No se paso informacion",Toast.LENGTH_SHORT).show();


        }else{
            ArrayList<Plan>plans=pe.getPlan();
            textNombreP.setText(pe.getNombre());
            textDescripP.setText(pe.getDescripcion());
            textCalifc.setText(Float.toString(pe.getValoracion()));
            Picasso.get().load(pe.getImagen()).into(imageP);
            calificP.setRating(pe.getValoracion());
            textDireccion.setText(pe.getDireccion());
            nombrePer.setText(pe.getPersona().getNombre());
            personNumber.setText("Telefono: "+pe.getPersona().getTelefono());
            correoPer.setText("Correo: "+pe.getPersona().getCorreo());

            if(plans.size()==2){
                linearLayout.setVisibility(View.VISIBLE);
                textTipoP.setText(plans.get(0).getTipo());
                textPrecioNum.setText("$"+Integer.toString(plans.get(0).getPrecio()));
                for(String s:plans.get(0).getServicio()){
                    chipCloud.addChip(s);
                }
                textTipoP2.setText(plans.get(1).getTipo());
                textPrecioNum2.setText("$"+Integer.toString(plans.get(1).getPrecio()));
                for(String s:plans.get(1).getServicio()){
                    chipCloud2.addChip(s);
                }

            }else{
                textTipoP.setText(plans.get(0).getTipo());
                textPrecioNum.setText("$"+Integer.toString(plans.get(0).getPrecio()));
                for(String s:plans.get(0).getServicio()){
                    chipCloud.addChip(s);
                }
            }

        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap=googleMap;

        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);



        LatLng universidad = new LatLng(11.223124, -74.185896);

        if (ActivityCompat.checkSelfPermission(getBaseContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getBaseContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }

        if (pe==null){
            Toast.makeText(getBaseContext(),"Error mapa",Toast.LENGTH_SHORT).show();

        }else {

            LatLng pension=new LatLng(pe.getCoordenadas().getLat(),pe.getCoordenadas().getLon());
            mMap.setMyLocationEnabled(true);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(universidad, 15));

            mMap.addMarker(new MarkerOptions().title(pe.getNombre()).snippet(pe.getPersona().getTelefono()).position(pension));
        }



    }
}
