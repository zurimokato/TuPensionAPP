package job.project.com.tupension.adapters;

import android.content.Context;
import android.content.Intent;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import job.project.com.tupension.Main2Activity;
import job.project.com.tupension.R;
import job.project.com.tupension.pojo.Pension;

public class PensionRescyclerViewAdapter extends RecyclerView.Adapter<PensionRescyclerViewAdapter.PesViewHolder> {
    private List<Pension>pensiones;
    private Context context;
    private LayoutInflater inflater;

    public PensionRescyclerViewAdapter(List<Pension> pensiones, Context context) {
        this.pensiones = pensiones;
        this.context = context;
        inflater=LayoutInflater.from(context);
    }


    @Override
    public PesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View roo=inflater.inflate(R.layout.card_pension,parent,false);
        PesViewHolder holder=new PesViewHolder(roo,context, pensiones);
        return holder;
    }

    @Override
    public void onBindViewHolder(PesViewHolder holder, int position) {
        Pension pension=pensiones.get(position);
        holder.textNombre.setText(pension.getNombre());
        holder.textDireccion.setText("Direccion: "+pension.getDireccion());
        holder.telefono.setText("Telefono: "+pension.getPersona().getTelefono());
        holder.ratingCalif.setRating(pension.getValoracion());
        Picasso.get().load(pension.getImagen()).into(holder.image);

    }

    @Override
    public int getItemCount() {
        return pensiones.size();
    }


    public  static class PesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView textNombre;
        private TextView textDireccion;
        private TextView telefono;
        private ImageView image;
        private RatingBar ratingCalif;
        private Context context;
        private List<Pension>pensiones;

        public PesViewHolder(View itemView, Context context, List<Pension> pensiones) {
            super(itemView);
            itemView.setOnClickListener(this);
            this.context = context;
            this.pensiones = pensiones;
            textNombre=(TextView) itemView.findViewById(R.id.textCardNombreP);
            textDireccion=(TextView) itemView.findViewById(R.id.textCardDireccionP);
            telefono=(TextView)itemView.findViewById(R.id.textTelefonoP);
            image=(ImageView) itemView.findViewById(R.id.pensionImage);
            ratingCalif=(RatingBar) itemView.findViewById(R.id.ratingBarCard);





        }

        @Override
        public void onClick(View v) {

            int pos=getAdapterPosition();
            Pension pe=pensiones.get(pos);
            System.out.println(pe.toString());
            Intent pasar=new Intent(this.context, Main2Activity.class);
            pasar.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            pasar.putExtra("pension",pe);
            this.context.startActivity(pasar);

        }
    }
}
