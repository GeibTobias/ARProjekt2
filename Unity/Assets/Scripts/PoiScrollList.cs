using UnityEngine;
using System.Collections;
using UnityEngine.UI;
using System.Collections.Generic;

[System.Serializable]
public class Item
{
	public string itemName;
	public Sprite image;
}

public class PoiScrollList : MonoBehaviour {

	public List<Item> itemList;
	public Transform contentPanel;
	public Text myItemCountDisplay;
	public SimpleObjectPool poiObjectPool;


	// Use this for initialization
	void Start () 
	{
		RefreshDisplay ();
	}

	void RefreshDisplay()
	{
		myItemCountDisplay.text = "POI Count: " + itemList.Count.ToString () + "/10";
		RemovePois ();
		AddPois ();
	}

	private void RemovePois()
	{
		while (contentPanel.childCount > 0) 
		{
			GameObject toRemove = transform.GetChild(0).gameObject;
			poiObjectPool.ReturnObject(toRemove);
		}
	}

	private void AddPois()
	{
		for (int i = 0; i < itemList.Count; i++) 
		{
			Item item = itemList[i];
			GameObject newPoi = poiObjectPool.GetObject();
			newPoi.transform.SetParent(contentPanel);

			SamplePoi samplePoi = newPoi.GetComponent<SamplePoi>();
			samplePoi.Setup(item, this);
		}
	}

	void AddItem(Item itemToAdd, PoiScrollList poiList)
	{
		poiList.itemList.Add (itemToAdd);

		RefreshDisplay ();
	}

	public void UpdateItemList(Item[] itemsToAdd, PoiScrollList poiList) {
		RemoveAllItems (poiList);

		for (int i = 0; i < itemsToAdd.Length; i++) {
			AddItem (itemsToAdd [i], poiList);
		}
	}

	public void RemoveItem(Item itemToRemove, PoiScrollList poiList)
	{
		for (int i = poiList.itemList.Count - 1; i >= 0; i--) 
		{
			if (poiList.itemList[i] == itemToRemove)
			{
				poiList.itemList.RemoveAt(i);
			}
		}

		RefreshDisplay ();
	}

	void RemoveAllItems(PoiScrollList poiList) {
		poiList.itemList.Clear();

		RefreshDisplay ();
	}

	public void MoveItemUp(Item item, PoiScrollList poiList) {
		int index = poiList.itemList.IndexOf (item);
		if (index > 0) {
			poiList.itemList.Remove (item);
			poiList.itemList.Insert (index - 1, item);
		}

		RefreshDisplay ();
	}

	public void MoveItemDown(Item item, PoiScrollList poiList) {
		int index = poiList.itemList.IndexOf (item);
		if (index < poiList.itemList.Count - 1) {
			poiList.itemList.Remove (item);
			poiList.itemList.Insert (index + 1, item);
		}

		RefreshDisplay ();
	}
}