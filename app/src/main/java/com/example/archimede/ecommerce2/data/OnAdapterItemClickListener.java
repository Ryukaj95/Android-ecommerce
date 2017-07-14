package com.example.archimede.ecommerce2.data;

/**
 * Created by archimede on 06/06/17.
 */

public interface OnAdapterItemClickListener {

    void OnItemClick(int position);
    void OnItemBuyClick(int position);
    void OnItemBookmarkClick(int position);
    void OnRemoveCartItem(int position);
}
