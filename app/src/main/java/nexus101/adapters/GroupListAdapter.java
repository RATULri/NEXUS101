package nexus101.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import nexus101.R;
import nexus101.admin.group.AdminGroupActivity;
import nexus101.listeners.GroupItemClickListener;
import nexus101.network.models.GroupsInfo;
import nexus101.network.models.Student;

public class GroupLIstAdapter extends RecyclerView.Adapter<GroupLIstAdapter.CardViewHolder> {

    Context context;
    List<GroupsInfo> groupsInfos;
    LayoutInflater inflater;
    GroupItemClickListener groupItemClickListener;

    public GroupLIstAdapter(AdminGroupActivity adminGroupActivity, List<GroupsInfo> groupsInfos, GroupItemClickListener groupItemClickListener) {
        this.context = adminGroupActivity;
        this.groupItemClickListener = groupItemClickListener;
        this.groupsInfos = groupsInfos;
        this.inflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_group, parent, false);
        CardViewHolder cardViewHolder = new CardViewHolder(view);
        return cardViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GroupLIstAdapter.CardViewHolder holder, int position) {
        holder.bind(groupsInfos.get(position));
    }

    @Override
    public int getItemCount() {
        return groupsInfos.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {

        TextView tv_name;

        public CardViewHolder(View itemView) {
            super(itemView);

            tv_name = (TextView) itemView.findViewById(R.id.group_name);
        }

        public void bind(final GroupsInfo groupsInfo) {

            tv_name.setText(groupsInfo.getGroupName());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    groupItemClickListener.onItemClick(groupsInfo);
                }
            });

        }
    }
}
