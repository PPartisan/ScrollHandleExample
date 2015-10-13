# ScrollHandleExample

[![YouTube: Scroll Handle Example](http://img.youtube.com/vi/YgcmQS_zPTY/0.jpg)](http://www.youtube.com/watch?v=YgcmQS_zPTY "YouTube: Scroll Handle Example")

Demonstrates how to progammatically scroll a `RecyclerView` by manipulating a `View` with a `TouchListener`. Although only a `RecyclerView` is shown in the code, the same method works just as well with `NestedScrollView`.

The method can be especially useful when constructing layouts that make use of `CollapsingToolbarLayout` from the Android Design Support Library. This is because layout elements beyond the `RecyclerView` can be added below the collapsing toolbar, yet still cause it to collapse and expand based on touch events in the same way as a `RecyclerView` or `NestedScrollView`.

A demonstration video is on [YouTube](https://www.youtube.com/watch?v=YgcmQS_zPTY&feature=youtu.be "YoutTube: Scroll Handle Example") and I will put together a blog post at [Partisan Apps](http://www.partisanapps.com/blog "Partisan Apps: Blog") that goes into more detail shortly.
