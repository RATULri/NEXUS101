package nexus101.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import nexus101.R;
import nexus101.network.models.FileInfo;

public class FileListAdapter extends RecyclerView.Adapter<FileListAdapter.CardViewHolder>{

    Context context;
    List<FileInfo> fileInfoList;
    LayoutInflater inflater;

    public FileListAdapter(Context context, List<FileInfo> fileInfos) {
        this.context = context;
        this.fileInfoList = fileInfos;
        this.inflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.card_list_file, parent, false);
        CardViewHolder cardViewHolder = new CardViewHolder(view);
        return cardViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        holder.bind(fileInfoList.get(position));
    }

    @Override
    public int getItemCount() {
        return fileInfoList.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {

        TextView tv_name;
        TextView tv_date;
        ImageButton btn_download;

        public CardViewHolder(View itemView) {
            super(itemView);

            tv_name = (TextView) itemView.findViewById(R.id.file_name);
            tv_date = itemView.findViewById(R.id.upload_date);
            btn_download = itemView.findViewById(R.id.btn_download);
        }

        public void bind(final FileInfo fileInfo) {

            tv_name.setText(fileInfo.getFileName());
            tv_date.setText(fileInfo.getUploadDate());

            btn_download.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(fileInfo.getFilePath()));
                    context.startActivity(browserIntent);
                }
            });

        }
    }
}
