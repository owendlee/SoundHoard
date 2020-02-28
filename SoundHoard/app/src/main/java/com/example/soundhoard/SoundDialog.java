package com.example.soundhoard;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import java.io.IOException;
import java.util.UUID;

import static android.app.Activity.RESULT_OK;

public class SoundDialog extends DialogFragment {
    public static final String CREATE_DIALOG = "create";
    public static final String UPDATE_DIALOG = "update";
    public static final String DELETE_DIALOG = "delete";

    public static final int SOUND_REQUEST_CODE = 1000;

    private Button importButton;
    private Button recordButton;
    private Uri uri;
    private EditText soundNameEditText;
    private SoundDialogListener listener;
    private int title;
    private int hint;
    private boolean isRecording = false;
    private MediaRecorder mediaRecorder;
    String path;

    public SoundDialog(String mode) {
        if(mode.equals("create")) {
            title = R.string.soundboard_activity_sound_dialog_title;
            hint = R.string.soundboard_activity_sound_dialog_hint;
        } else if(mode.equals("update")) {

        } else if(mode.equals("delete")) {

        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        if(title == R.string.soundboard_activity_sound_dialog_title) {
            LayoutInflater inflater = getActivity().getLayoutInflater();
            View view = inflater.inflate(R.layout.activity_soundboard_dialog, null);

            builder.setView(view)
                    .setTitle(title)
                    .setPositiveButton(R.string.okay, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            if(uri == null) {
                                Toast.makeText(getActivity(), "Must import or record a sound...", Toast.LENGTH_LONG).show();
                            } else {
                                String soundName = soundNameEditText.getText().toString();
                                listener.applySoundText(soundName, uri);
                            }
                        }
                    })
                    .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });

            recordButton = view.findViewById(R.id.recordButton);
            recordButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(isRecording) {
                        mediaRecorder.stop();
                        uri = Uri.parse(path);
                        isRecording = false;
                        recordButton.setText(R.string.sound_dialog_start_record);
                        Toast.makeText(getActivity(), "Finished Recording!", Toast.LENGTH_LONG).show();

                        importButton.setError(null);
                        recordButton.setError(null);
                    } else {
                        try {
                            path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + UUID.randomUUID().toString() + "_SoundHoard.3gp";

                            mediaRecorder = new MediaRecorder();
                            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                            mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
                            mediaRecorder.setOutputFile(path);
                            mediaRecorder.prepare();
                            mediaRecorder.start();

                            Toast.makeText(getActivity(), "RECORDING...", Toast.LENGTH_LONG).show();
                            isRecording = true;
                            recordButton.setText(R.string.sound_dialog_stop_record);
                        } catch(IOException e) {
                            Toast.makeText(getActivity(), "Could not record sound...", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            });

            importButton = view.findViewById(R.id.importButton);
            importButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.setType("audio/*");
                    intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
                    startActivityForResult(Intent.createChooser(intent, "open"), SOUND_REQUEST_CODE);
                }
            });

            soundNameEditText = view.findViewById(R.id.dialogInput);
            soundNameEditText.setHint(hint);

            importButton.setError("Must import or record a sound...");
            recordButton.setError("Must import or record a sound...");
        } else {

        }

        return builder.create();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == SOUND_REQUEST_CODE) {
            if(resultCode == RESULT_OK) {
                uri = data.getData();
                getActivity().getContentResolver().takePersistableUriPermission(uri, Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                
                importButton.setError(null);
                recordButton.setError(null);
            } else {
                uri = null;

                importButton.setError("Must import or record a sound...");
                recordButton.setError("Must import or record a sound...");
            }
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (SoundDialogListener)context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement SoundboardDialogListener.");
        }
    }

    public interface SoundDialogListener {
        void applySoundText(String name, Uri uri);
        void applySoundDelete();
    }
}