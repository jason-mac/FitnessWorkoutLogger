#!/usr/bin/env python3
"""
Pushing script because I am lazy
"""

import subprocess

def run():
    """
    run function
    """
    try:
        subprocess.run(["git", "add", "."], check=True)
        commit_message = input("Commit Message: ")
        if(commit_message == ""):
            commit_message = "Term Project"
        subprocess.run(["git", "commit", "-m", commit_message], check=True)
        subprocess.run(["git", "push"], check=True)
    except subprocess.CalledProcessError as e:
        print(f"An error occurred: {e}")

if __name__ == "__main__":
    run()
