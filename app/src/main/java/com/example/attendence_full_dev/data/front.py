import tkinter as tk
from tkinter import filedialog
import os
import librosa
import scipy.io.wavfile
import numpy as np
from sklearn.neural_network import MLPClassifier

# Define the function to extract audio features
def extract_feature(file_name, mfcc, chroma, mel):
    sample_rate, X = scipy.io.wavfile.read(file_name)
    # convert integer data to floating-point data
    X = X.astype(np.float32) / np.iinfo(X.dtype).max
    if chroma:
        stft=np.abs(librosa.stft(X))
    result=np.array([])
    if mfcc:
        mfccs=np.mean(librosa.feature.mfcc(y=X, sr=sample_rate, n_mfcc=40).T, axis=0)
        result=np.hstack((result, mfccs))
    if chroma:
        chroma=np.mean(librosa.feature.chroma_stft(S=stft, sr=sample_rate).T,axis=0)
        result=np.hstack((result, chroma))
    if mel:
        mel=np.mean(librosa.feature.melspectrogram(y=X, sr=sample_rate).T,axis=0)
        result=np.hstack((result, mel))
    return result

# Define the function to handle the button click event
def browse_file():
    file_path = filedialog.askopenfilename()
    # Pass the selected file path to the extract_feature function
    features = extract_feature(file_path, mfcc=True, chroma=True, mel=True)
    # Predict emotion from features
    emotion = clf.predict(features.reshape(1, -1))[0]
    result_label.config(text=f"The emotion of the audio file is {emotion}")

# Load the trained MLPClassifier model
clf = pickle.load(open("model.pkl", "rb"))

# Create the GUI application
root = tk.Tk()
root.title("Emotion Recognition from Audio")

# Create a button for file selection
browse_button = tk.Button(root, text="Select Audio File", command=browse_file)
browse_button.pack(pady=20)

# Create a label to display the result
result_label = tk.Label(root, text="")
result_label.pack()

root.mainloop()