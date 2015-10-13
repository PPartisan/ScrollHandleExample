# ScrollHandleExample
Demonstrates how to progammatically scroll a `RecyclerView` with a `TouchListener` assigned to a `View`. Although only a `RecyclerView` is shown in the code, the same method works just as well with `NestedScrollView`.

The method can be especially useful when constructing layouts that make use of `CollapsingToolbarLayout` from the Android Design Support Library. This is because layout elements beyond the `RecyclerView` can be added below the collapsing toolbar, yet still cause it to collapse and expand based on touch events in the same way as a `RecyclerView` or `NestedScrollView`.

A demonstration video will soon be uploaded to [YouTube](https://www.youtube.com/watch?v=YgcmQS_zPTY&feature=youtu.be "YoutTube: Scroll Handle Example") and I will put together a blog post at [Partisan Apps](http://www.partisanapps.com/blog "Partisan Apps: Blog") that goes into more detail shortly.
