package com.example.animalapp;




import android.os.Bundle;
import android.widget.GridView;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class AnimalList extends AppCompatActivity {

    GridView gridView;
    ArrayList<Animal> list;
    AnimalListAdapter adapter = null;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animal_list_activity);

//        gridView = (GridView) findViewById(R.id.gridView);
//        list = new ArrayList<>();
//        adapter = new AnimalListAdapter(this, R.layout.animal_items, list);
//        gridView.setAdapter(adapter);
//
//        // get all data from sqlite
//        Cursor cursor = MainActivity.sqLiteHelper.getData("SELECT * FROM ANIMAL");
//        list.clear();
//        while (cursor.moveToNext()) {
//            int id = cursor.getInt(0);
//            String name = cursor.getString(1);
//            String description = cursor.getString(2);
//            byte[] image = cursor.getBlob(3);
//
//            list.add(new Animal(name, description, image, id));
//        }
//        adapter.notifyDataSetChanged();
//
//        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
//
//                CharSequence[] items = {"Update", "Delete"};
//                AlertDialog.Builder dialog = new AlertDialog.Builder(AnimalList.this);
//
//                dialog.setTitle("Choose an action");
//                dialog.setItems(items, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int item) {
//                        if (item == 0) {
//                            // update
//                            Cursor c = MainActivity.sqLiteHelper.getData("SELECT id FROM ANIMAL");
//                            ArrayList<Integer> arrID = new ArrayList<Integer>();
//                            while (c.moveToNext()){
//                                arrID.add(c.getInt(0));
//                            }
//                            // show dialog update at here
//                            showDialogUpdate(AnimalList.this, arrID.get(position));
//
//                        } else {
//                            // delete
//                            Cursor c = MainActivity.sqLiteHelper.getData("SELECT id FROM ANIMAL");
//                            ArrayList<Integer> arrID = new ArrayList<Integer>();
//                            while (c.moveToNext()){
//                                arrID.add(c.getInt(0));
//                            }
//                            showDialogDelete(arrID.get(position));
//                        }
//                    }
//                });
//                dialog.show();
//                return true;
//            }
//        });
    }

//    ImageView imageViewAnimal;
//    private void showDialogUpdate(Activity activity, final int position){
//
//        final Dialog dialog = new Dialog(activity);
//        dialog.setContentView(R.layout.update_animal_activity);
//        dialog.setTitle("Update");
//
//        imageViewAnimal = (ImageView) dialog.findViewById(R.id.imageViewAnimal);
//        final EditText edtName = (EditText) dialog.findViewById(R.id.edtName);
//        final EditText edtDescription = (EditText) dialog.findViewById(R.id.edtDescription);
//        Button btnUpdate = (Button) dialog.findViewById(R.id.btnUpdate);
//
//        // set width for dialog
//        int width = (int) (activity.getResources().getDisplayMetrics().widthPixels * 0.95);
//        // set height for dialog
//        int height = (int) (activity.getResources().getDisplayMetrics().heightPixels * 0.7);
//        dialog.getWindow().setLayout(width, height);
//        dialog.show();
//
//        imageViewAnimal.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // request photo library
//                ActivityCompat.requestPermissions(
//                        AnimalList.this,
//                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
//                        888
//                );
//            }
//        });
//
//        btnUpdate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                try {
//                    MainActivity.sqLiteHelper.updateData(
//                            edtName.getText().toString().trim(),
//                            edtDescription.getText().toString().trim(),
//                            MainActivity.imageViewToByte(imageViewAnimal),
//                            position
//                    );
//                    dialog.dismiss();
//                    Toast.makeText(getApplicationContext(), "Update successfully!!!", Toast.LENGTH_SHORT).show();
//                }
//                catch (Exception error) {
//                    Log.e("Update error", error.getMessage());
//                }
//                updateAnimalList();
//            }
//        });
//    }
//
//    private void showDialogDelete(final int idAnimal){
//        final AlertDialog.Builder dialogDelete = new AlertDialog.Builder(AnimalList.this);
//
//        dialogDelete.setTitle("Warning!!");
//        dialogDelete.setMessage("Are you sure you want to this delete?");
//        dialogDelete.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                try {
//                    MainActivity.sqLiteHelper.deleteData(idAnimal);
//                    Toast.makeText(getApplicationContext(), "Delete successfully!!!", Toast.LENGTH_SHORT).show();
//                } catch (Exception e){
//                    Log.e("error", e.getMessage());
//                }
//                updateAnimalList();
//            }
//        });
//
//        dialogDelete.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//            }
//        });
//        dialogDelete.show();
//    }
//
//    private void updateAnimalList(){
//        // get all data from sqlite
//        Cursor cursor = MainActivity.sqLiteHelper.getData("SELECT * FROM ANIMAL");
//        list.clear();
//        while (cursor.moveToNext()) {
//            int id = cursor.getInt(0);
//            String name = cursor.getString(1);
//            String description = cursor.getString(2);
//            byte[] image = cursor.getBlob(3);
//
//            list.add(new Animal(name, description, image, id));
//        }
//        adapter.notifyDataSetChanged();
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//
//        if(requestCode == 888){
//            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
//                Intent intent = new Intent(Intent.ACTION_PICK);
//                intent.setType("image/*");
//                startActivityForResult(intent, 888);
//            }
//            else {
//                Toast.makeText(getApplicationContext(), "You don't have permission to access file location!", Toast.LENGTH_SHORT).show();
//            }
//            return;
//        }
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//
//        if(requestCode == 888 && resultCode == RESULT_OK && data != null){
//            Uri uri = data.getData();
//            try {
//                InputStream inputStream = getContentResolver().openInputStream(uri);
//                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
//                imageViewAnimal.setImageBitmap(bitmap);
//
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//        }
//
//        super.onActivityResult(requestCode, resultCode, data);
//    }
}