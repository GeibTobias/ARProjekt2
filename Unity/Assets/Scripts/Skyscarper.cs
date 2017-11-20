using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using System;

public class Skyscarper : MonoBehaviour {

	public string poiID;
	public string poiName;
	public Sprite poiImage;
	private GameObject content;
	private PoiScrollList scrollList;


	// Use this for initialization
	void Start () {
	}
	
	// Update is called once per frame
	void Update () {
		if (Input.GetMouseButtonDown (0)) {
			content = GameObject.Find ("Content");
			scrollList = content.GetComponent<PoiScrollList>();
			Debug.Log (scrollList);
			RaycastHit hit;
			Ray ray = Camera.main.ScreenPointToRay (Input.mousePosition);

			if (Physics.Raycast (ray, out hit)) {
				BoxCollider bc = hit.collider as BoxCollider;
				if (bc != null) {
					scrollList.AddItem (new Item(poiName, poiImage));
					for (int i = 0; i < scrollList.itemList.Count; i++) {
						if (scrollList.itemList.Count != 0) {
							Debug.Log (scrollList.itemList [i].itemName);					
					
						}
					}
				}
			}
		}
	}
}
