<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="text" indent="no"/>
<xsl:strip-space elements="*"/>

<!--x are y that..-->
<xsl:template match="S[count(S)=0][NP[count(DT)=0 and NNS[@nsubj]] and VP[(VB|VBZ|VBP)[@cop and @lemma='be'] and NP[NP][SBAR] and count(*)=2]]">
SubClassOf(
	<xsl:apply-templates select="NP"/>
	<xsl:text> </xsl:text>
	<xsl:apply-templates select="VP/NP"/>)
</xsl:template>

<!-- each x can be a y -->
<xsl:template match="S[count(S)=0][NP[*[@det and position()=1]][*[@nsubj]] and VP[MD[@aux] and VP[(VB|VBP)[position()=1 and @cop]][NP[count(SBAR)=0]]]]">
SubClassOf(
	<xsl:apply-templates select="NP"/>
	<xsl:text> </xsl:text>
	<xsl:apply-templates select="VP"/>)
</xsl:template>

<!-- x verb y -->
<xsl:template match="S[count(S)=0][VP/(VB|VBZ|VBP)/@id=@root and NP[NN|NNS] and VP[(VB|VBZ|VBP)[@lemma!='have']] and VP[NP]]">
SubClassOf(
	<xsl:apply-templates select="NP"/>
	<xsl:text> </xsl:text>
	<xsl:apply-templates select="VP"/>)
</xsl:template>

<xsl:template match="S[count(S)=0][NP[count(NP)=0 and count(CC)>0 and count(NNS)>0] and VP[VBP[position()=1 and @cop]][NP]]">
<xsl:for-each select="NP/NNS">
SubClassOf(
    <xsl:call-template name="noun_to_class">
            <xsl:with-param name="noun" select="." />
    </xsl:call-template>
    <xsl:text> </xsl:text>
    <xsl:apply-templates select="../../VP"/>)
</xsl:for-each>
</xsl:template>

<xsl:template match="S[count(S)=0][NP[.//(NN|NNS)[@nsubj] and count(CC)=0] and VP[(VB|VBZ|VBP)[position()=1 and @cop]][NP[count(SBAR)=0]]]">
SubClassOf(
	<xsl:apply-templates select="NP"/>
	<xsl:text> </xsl:text>
	<xsl:apply-templates select="VP"/>)
</xsl:template>

<xsl:template match="S[count(S)=0][NP[.//(NN|NNS)[@nsubj] and count(CC)=0] and VP[(VB|VBZ|VBP)[@lemma='have']][NP[count(SBAR)=0]]]">
SubClassOf(
	<xsl:apply-templates select="NP"/>
	<xsl:text> </xsl:text>
	<xsl:apply-templates select="VP"/>)
</xsl:template>

<xsl:template match="S[count(S)=0][NP[name(*[1])='DT' and (NNP|NNPS|PRP)[@nsubj]] and VP[(VB|VBZ|VBP)[position()=1 and @cop]][NP[count(SBAR)=0]]]">
SubClassOf(
	<xsl:apply-templates select="NP"/>
	<xsl:text> </xsl:text>
	<xsl:apply-templates select="VP"/>)
</xsl:template>

<xsl:template match="S[count(S)=0][VP/(VB|VBZ|VBP)/@id=@root and NP[NNP|NNPS|PRP] and VP[count(*[@lemma='have'])=0 and NP[count(NNP|NNPS|PRP)=0]]]">
SubClassOf(
	<xsl:apply-templates select="VP/NP"/>
	<xsl:text> </xsl:text>
	ObjectHasValue(ObjectInverseOf(
        <xsl:call-template name="verb_to_object_property">
		<xsl:with-param name="verb" select="VP/(VB|VBZ|VBP)" />
	</xsl:call-template>) 
        <xsl:text> </xsl:text>
	<xsl:call-template name="noun_phrase_to_individual">
            <xsl:with-param name="NP" select="NP" />
        </xsl:call-template>))
</xsl:template>

<xsl:template match="S[count(S)=0][VP/(VB|VBZ|VBP)/@id=@root and VP/(VB|VBZ|VBP)/@id=VP/S/VP/VP/VB/@xcomp and NP[NNP|NNPS|PRP] and VP[S/VP[TO and VP/NP[count(*[@entity])=0]]]]">
SubClassOf(
	<xsl:apply-templates select="VP/S/VP/VP/NP"/>
	<xsl:text> </xsl:text>
	ObjectHasValue(ObjectInverseOf(
        <xsl:call-template name="verb_and_xcomp_to_object_property">
            <xsl:with-param name="verb" select="VP/(VB|VBZ|VBP)" />
            <xsl:with-param name="xcomp" select="VP/S/VP/VP/VB" />
	</xsl:call-template>) 
        <xsl:text> </xsl:text>
        <xsl:call-template name="noun_phrase_to_individual">
            <xsl:with-param name="NP" select="NP" />
        </xsl:call-template>))
</xsl:template>

</xsl:stylesheet>