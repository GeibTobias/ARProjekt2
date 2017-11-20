using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using System;

public class Skyscarper : MonoBehaviour {

	public string poiID;
	public string poiName;
	public Sprite poiImage;

	// Use this for initialization
	void Start () {
		
	}
	
	// Update is called once per frame
	void Update () {
		if (Input.GetMouseButtonDown (0)) {
			RaycastHit hit;
			Ray ray = Camera.main.ScreenPointToRay (Input.mousePosition);

			if (Physics.Raycast (ray, out hit)) {
				BoxCollider bc = hit.collider as BoxCollider;
				if (bc != null) {
					Debug.Log ("Success!");
				}
			}
		}
	}
}
