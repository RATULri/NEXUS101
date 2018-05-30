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
import nexus101.network.models.GroupInfo;

public class GroupListAdapter extends RecyclerView.Adapter<GroupListAdapter.CardViewHolder> {

    Context context;
    List<GroupInfo> groupInfo;
    LayoutInflater inflater;
    GroupItemClickListener groupItemClickListener;

    public GroupListAdapter(AdminGroupActivity adminGroupActivity, List<GroupInfo> groupInfo, GroupItemClickListener groupItemClickListener) {
        this.context = adminGroupActivity;
        this.groupItemClickListener = groupItemClickListener;
        this.groupInfo = groupInfo;
        this.inflater = LayoutInflater.from(context);
    }


        @NonNull
        @Override
        public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_course_and_group, parent, false);
        CardViewHolder cardViewHolder = new GroupListAdapter.CardViewHolder(view);
        return cardViewHolder;
    }

        @Override
        public void onBindViewHolder(@NonNull GroupListAdapter.CardViewHolder holder, int position) {
            holder.bind(groupInfo.get(position));
        }

        @Override
        public int getItemCount() {
            return groupInfo.size();
        }

        public class CardViewHolder extends RecyclerView.ViewHolder {

            TextView tv_name;

            public CardViewHolder(View itemView) {
                super(itemView);

                tv_name = (TextView) itemView.findViewById(R.id.course_or_group_name);
            }

            public void bind(final GroupInfo groupInfo) {

                tv_name.setText(groupInfo.getGroupName());

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        groupItemClickListener.onItemClick(groupInfo);
                    }
                });

            }
        }
}
